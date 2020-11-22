package com.outgo.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.outgo.domain.Buyer;
import com.outgo.domain.Status;
import com.outgo.form.SlackPostForm;
import com.outgo.form.SlackPostForm.Action;
import com.outgo.form.SlackPostForm.Attachment;
import com.outgo.repository.CompletedMessageRepository;

import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class OutgoBot extends SlackBot {
    private Buyer buyer;
    private long amount;
    private String category;
    private boolean confirmed;

    public OutgoBot(
            String slackTokenBotAccess,
            String slackUrlPostMessage) {
        super(slackTokenBotAccess, slackUrlPostMessage);
    }

    public OutgoBot(
            String slackTokenBotAccess,
            String slackUrlPostMessage,
            Buyer buyer,
            long amount,
            String category) {
        super(slackTokenBotAccess, slackUrlPostMessage);
        this.buyer = buyer;
        this.amount = amount;
        this.category = category;
    }

    public Outgo toOutgo() {
        return new Outgo(
                0,
                Status.NON_PROCESSING,
                buyer,
                amount,
                category);
    }

    public void clear() {
        amount = 0;
        category = null;
        confirmed = false;
    }

    public void reply(CompletedMessageRepository completedMessageRepository) {
        if (confirmed) {
            postMessage("outgo", completedMessageRepository.findAll().getOneAtRandom());
            clear();
        } else if (amount < 1) {
            postMessage("outgo", "Enter the amount of money.");
        } else if (category == null) {
            postCategoryList();
        } else {
            postConfirmButton();
        }
    }

    private void postCategoryList() {
        SlackPostForm slackPostForm = new SlackPostForm();
        slackPostForm.setChannel("outgo");
        slackPostForm.setText("Select a category.");
        Attachment attachment = new Attachment();
        attachment.setCallback_id("select_category");
        Action action = new Action();
        action.setName("category_list");
        action.setText("Select category...");
        action.setType("select");
        action.setOptions(SlackPostForm.buildOptions(
            "食費",
            "日用品",
            "外食費",
            "娯楽",
            "勉強",
            "光熱費",
            "家賃"
        ));
        List<Action> actions = new ArrayList<>(1);
        actions.add(action);
        attachment.setActions(actions);
        List<Attachment> attachments = new ArrayList<>(1);
        attachments.add(attachment);
        slackPostForm.setAttachments(attachments);
        RequestEntity<SlackPostForm> req = RequestEntity
                .post(URI.create(slackUrlPostMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + slackTokenBotAccess)
                .body(slackPostForm);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(req, SlackPostForm.class);
    }

    private void postConfirmButton() {
        SlackPostForm slackPostForm = new SlackPostForm();
        slackPostForm.setChannel("outgo");
        slackPostForm.setText(String.format("If there is no problem, press OK.\n  Amount:%s\n Category:%s",
                    String.valueOf(amount), category));
        Attachment attachment = new Attachment();
        attachment.setCallback_id("confirm");
        Action action1 = new Action();
        action1.setName("confirm");
        action1.setText("OK");
        action1.setValue("OK");
        action1.setType("button");
        Action action2 = new Action();
        action2.setName("confirm");
        action2.setText("NG");
        action2.setValue("NG");
        action2.setType("button");
        List<Action> actions = new ArrayList<>(2);
        actions.add(action1);
        actions.add(action2);
        attachment.setActions(actions);
        List<Attachment> attachments = new ArrayList<>(1);
        attachments.add(attachment);
        slackPostForm.setAttachments(attachments);
        RequestEntity<SlackPostForm> req = RequestEntity
                .post(URI.create(slackUrlPostMessage))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer " + slackTokenBotAccess)
                .body(slackPostForm);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(req, SlackPostForm.class);
    }
}
