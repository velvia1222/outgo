<template>
  <section class="section">
    <b-field>
      <button class="button" @click="goInput">Back</button>
    </b-field>
    <b-table
      :data="data"
      checkable>
      <template slot-scope="props">
        <b-table-column field="buyer" label="Buyer">
          {{ props.row.buyer }}
        </b-table-column>
        <b-table-column field="amount" label="Amount">
          {{ props.row.amount }}
        </b-table-column>
        <b-table-column field="category" label="Category">
          {{ props.row.category }}
        </b-table-column>
        <b-table-column field="edit" label="Edit">
          <button class="button" @click="edit(props.index)">Edit</button>
        </b-table-column>
        <b-table-column field="remove" label="Remove">
          <button class="button" @click="remove(props.row.id)">Remove</button>
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
      data: [
        {
          id: 1,
          buyer: 'y',
          amount: '400',
          category: '娯楽'
        },
        {
          id: 2,
          buyer: 'n',
          amount: '200',
          category: '食費'
        },
        {
          id: 3,
          buyer: 'n',
          amount: '100',
          category: '日用品'
        },
        {
          id: 4,
          buyer: 'y',
          amount: '300',
          category: '食費'
        }
      ]
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.loading = true
      axios({
        url: '/api/outgoes/',
        method: 'GET',
        headers: {
          'X-XSRF-TOKEN': Cookies.get('XSRF-TOKEN')
        }
      }).then(response => {
        this.data = response.data
        this.loading = false
      })
    },
    edit(index) {
      this.$router.push({name: 'Outgoinput', params: {
          id: this.data[index].id,
          amount: this.data[index].amount,
          buyer: this.data[index].buyer,
          category: this.data[index].category
      }})
    },
    remove(index) {
      alert(index)
    },
    goInput() {
      this.$router.push("/outgoinput")
    }
  }
}
</script>
