<template>
  <section class="section" style="width:550px">
    <b-field>
      <button class="button" @click="goInput">Back</button>
      <button class="button is-info" @click="confirmPay">Pay</button>
    </b-field>
    <b-table
      :data="outgoes"
      :checked-rows.sync="checkedRows"
      :mobile-cards="false"
      checkable>
      <template slot-scope="props">
        <b-table-column field="buyer" label="Buyer" width="70">
          {{ props.row.buyer }}
        </b-table-column>
        <b-table-column field="amount" label="Amount" width="100" numeric>
          {{ props.row.amount }}
        </b-table-column>
        <b-table-column field="category" label="Category">
          {{ props.row.category }}
        </b-table-column>
        <b-table-column field="created_at" label="created_at">
          {{ props.row.created_at }}
        </b-table-column>
        <b-table-column width="110">
          <nobr>
            <button class="button" @click="edit(props.index)">Edit</button>
            <button class="button" @click="confirmRemove(props.index)">Remove</button>
          </nobr>
        </b-table-column>
      </template>
    </b-table>
  </section>
</template>

<script>
import axios from 'axios'
import Cookies from 'js-cookie'

export default {
  name: 'Outgolist',
  data() {
    return {
      loading: false,
      outgoes: [],
      checkedRows: []
    }
  },
  created() {
    this.fetchOutgoes()
  },
  watch: {
    '$route': 'fetchOutgoes'
  },
  methods: {
    fetchOutgoes() {
      this.loading = true
      axios({
        url: '/api/outgoes/',
        method: 'GET',
        headers: {
          'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
        }
      }).then(response => {
        this.outgoes = response.data
        this.loading = false
      })
    },
    edit(index) {
      this.$router.push({name: 'Outgoinput', params: {
          id: this.outgoes[index].id,
          amount: this.outgoes[index].amount,
          buyer: this.outgoes[index].buyer,
          category: this.outgoes[index].category
      }})
    },
    confirmRemove(index) {
      let outgo = this.outgoes[index]
      this.$dialog.confirm({
        message: outgo.buyer + " " + outgo.amount + "を削除します",
        onConfirm: () => this.remove(index)
      })
    },
    remove(index) {
      this.loading = true
      let ids = []
      ids.push(this.outgoes[index].id)
      axios({
        url: '/api/outgoes/',
        method: 'DELETE',
        headers: {
          'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
        },
        data: {ids: ids}
      }).then(response => {
        this.fetchOutgoes()
        this.loading = false
      })
    },
    goInput() {
      this.$router.push("/outgoinput")
    },
    confirmPay() {
      let amountY = 0
      let amountN = 0
      let payment = 0
      let payer = ''
      for (let outgo of this.checkedRows) {
        if (outgo.buyer === 'y') {
          amountY += Number(outgo.amount)
        } else {
          amountN += Number(outgo.amount)
        }
      }
      if (amountN < amountY) {
        payer = 'n'
        payment = Math.floor((amountY - amountN) / 2)
      } else {
        payer = 'y'
        payment = Math.floor((amountN - amountY) / 2)
      }
      this.$dialog.confirm({
        message: payer + "が" + payment + "円支払ってください",
        onConfirm: () => this.pay()
      })
    },
    pay() {
      this.loading = true
      let ids = []
      for (let outgo of this.checkedRows) {
        ids.push(outgo.id)
      }
      axios({
        url: '/api/outgoes/pay',
        method: 'POST',
        headers: {
          'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
        },
        data: {ids: ids}
      }).then(response => {
        this.$toast.open("Pay!")
        this.fetchOutgoes()
        this.loading = false
      })
    }
  }
}
</script>
