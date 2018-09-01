<template>
  <section class="section">
    <b-field>
      <b-input v-model="amount" placeholder="outgo"></b-input>
    </b-field>
    <b-field>
      <b-select v-model="buyer" placeholder="Select a name">
        <option value="n">n</option>
        <option value="y">y</option>
      </b-select>
    </b-field>
    <b-field>
      <b-select v-model="category" placeholder="Select a category">
        <option value="食費">食費</option>
        <option value="日用品">日用品</option>
        <option value="外食費">外食費</option>
        <option value="娯楽">娯楽</option>
        <option value="光熱費">光熱費</option>
        <option value="家賃">家賃</option>
      </b-select>
    </b-field>
    <a class="button" v-on:click="enter">Enter</a><a class="button">Cancel</a>
  </section>
</template>

<script>
import axios from 'axios'
import Cookies from 'js-cookie'

export default {
  name: 'Outgoinput',
  methods: {
    data() {
      return {
        amount: '',
        buyer: '',
        category: '',
      }
    },
    enter() {
      axios({
        url: '/api/outgoes/',
        method: 'POST',
        headers: {
          'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
        },
        data: {
          'amount': this.amount,
          'buyer': this.buyer,
          'category': this.category,
        }
      })
    }
  }
}
</script>
