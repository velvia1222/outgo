<template>
  <section class="section">
    <div class="container">
      <b-field>
        <input class="input" v-model="amount" @keyup.enter="enter">
      </b-field>
      <b-field>
        <div class="select">
          <select v-model="buyer" @keyup.enter="enter">
            <option>n</option>
            <option>y</option>
          </select>
        </div>
      </b-field>
      <b-field>
        <div class="select">
          <select v-model="category" @keyup.enter="enter">
            <option>食費</option>
            <option>日用品</option>
            <option>外食費</option>
            <option>娯楽</option>
            <option>光熱費</option>
            <option>家賃</option>
          </select>
        </div>
      </b-field>
      <b-field>
        <button class="button" @click="goList">Cancel</button>
        <button class="button is-info" @click="enter">Enter</button>
      </b-field>
    </div>
  </section>
</template>

<script>
import axios from 'axios'
import Cookies from 'js-cookie'

export default {
  name: 'Outgoinput',
  data() {
    if ('id' in this.$route.params) {
      this.id = this.$route.params.id
      return {
        amount: this.$route.params.amount,
        buyer: this.$route.params.buyer,
        category: this.$route.params.category
      }
    } else {
      return {
        amount: '',
        buyer: 'n',
        category: '食費'
      }
    }
  },
  methods: {
    enter() {
      let data = {
        'amount': this.amount,
        'buyer': this.buyer,
        'category': this.category,
      }
      if ('id' in this) {
        data['id'] = this.id
      }
      axios({
        url: '/api/outgoes/',
        method: 'POST',
        headers: {
          'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
        },
        data: data
      }).then(response => {
        this.$router.push("/outgoes")
      })
    },
    goList() {
      this.$router.push("/outgoes")
    }
  }
}
</script>
