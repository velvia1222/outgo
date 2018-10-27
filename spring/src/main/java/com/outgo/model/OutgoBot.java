package com.outgo.model;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.outgo.domain.Buyer;
import com.outgo.domain.Status;
import com.outgo.form.SlackPostForm;
import com.outgo.form.SlackPostForm.Action;
import com.outgo.form.SlackPostForm.Attachment;
import com.outgo.form.SlackPostForm.Option;

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

    public void reply() {
        if (confirmed) {
            List<String> confirmMessages = new ArrayList<>();
            confirmMessages.add("May the force be with you.");
            confirmMessages.add("Patience you must have, my young Padawan.");
            confirmMessages.add("Power! Unlimited power!");
            confirmMessages.add("The time has come. Execute Order 66.");
            confirmMessages.add("It's over Anakin! I have the high ground!");
            confirmMessages.add("The Emperor is not as forgiving as I am.");
            confirmMessages.add("I am your father.");
            postMessage("outgo", confirmMessages.get(
                        new Random().nextInt(confirmMessages.size())));
            clear();
        } else if (amount < 1) {
            postMessage("outgo", "金額を入力せよ！");
        } else if (category == null) {
            postCategoryList();
        } else {
            postConfirmButton();
        }
    }

    private void postCategoryList() {
        SlackPostForm slackPostForm = new SlackPostForm();
        slackPostForm.setChannel("outgo");
        slackPostForm.setText("カテゴリを選択せよ！");
        Attachment attachment = new Attachment();
        attachment.setCallback_id("select_category");
        Action action = new Action();
        action.setName("category_list");
        action.setText("Select category...");
        action.setType("select");
        Option option1 = new Option();
        option1.setText("食費");
        option1.setValue("食費");
        Option option2 = new Option();
        option2.setText("日用品");
        option2.setValue("日用品");
        Option option3 = new Option();
        option3.setText("外食費");
        option3.setValue("外食費");
        Option option4 = new Option();
        option4.setText("娯楽");
        option4.setValue("娯楽");
        Option option5 = new Option();
        option5.setText("光熱費");
        option5.setValue("光熱費");
        Option option6 = new Option();
        option6.setText("家賃");
        option6.setValue("家賃");
        List<Option> options = new ArrayList<>(2);
        options.add(option1);
        options.add(option2);
        options.add(option3);
        options.add(option4);
        options.add(option5);
        options.add(option6);
        action.setOptions(options);
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
        slackPostForm.setText(String.format("問題が無ければOKを押すべし！\n　金額：%s\n　カテゴリ：%s",
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
