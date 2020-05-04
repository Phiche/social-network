<template>
  <div class="people">
    <h1>{{ msg }}</h1>
    <h2>REST service call results</h2>

    <button @click="callHelloApi()">CALL Spring Boot REST backend service</button>

    <h4>Backend response: {{ backendResponse }}</h4>

    <div class="people">
      <div class="container">
        <ul class="people-list">
          <li class="media" v-for="person in people" :key="person.id">
            <div class="media-left">
            </div>
            <div class="media-body">
              <h4 class="media-heading"></h4>
              <h5><i>by {{ person.name }} {{person.surname}}</i></h5>
              <button @click="addFriend(person.id)">Add friend</button>
            </div>
          </li>
        </ul>
      </div>
    </div>

  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'service',

    data () {
      return {
        people: [],
        msg: 'HowTo call REST-Services:',
        backendResponse: [],
        errors: []
      }
    },
    methods: {
      // Fetches posts when the component is created.
      loadPeople() {
        api.getPeople().then(response => {
          this.people = response.data
        })
      },
      addFriend(friendId) {
        api.addFriend(this.$store.state.personId, friendId, new Date())
      },
      callHelloApi () {
        api.hello().then(response => {
            this.backendResponse = response.data;
            console.log(response.data)
        })
        .catch(error => {
          this.errors.push(error)
        })
      }
    },
    created() {
      this.loadPeople()
    }
  }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  h1, h2 {
    font-weight: normal;
  }

  ul {
    list-style-type: none;
    padding: 0;
  }

  li {
    display: inline-block;
    margin: 0 10px;
  }

  a {
    color: #42b983;
  }
</style>
