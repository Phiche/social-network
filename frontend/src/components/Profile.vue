<template>
  <div class="profile">
    <h1>Your Profile</h1>

    <h3>Just some database interaction...</h3>
    <input type="text" v-model="profile.name" ref="name" :value="profile.name" :disabled="!isEditing" :class="{view: !isEditing}"> <br>
    <input type="text" v-model="profile.surname" ref="surname" :value="profile.surname" :disabled="!isEditing" :class="{view: !isEditing}"><br>
    <input type="text" v-model="profile.patronymic" ref="patronymic" :value="profile.name" :disabled="!isEditing" :class="{view: !isEditing}"><br>
    <input type="number" v-model="profile.age" ref="age" :value="profile.age" :disabled="!isEditing" :class="{view: !isEditing}"><br>
    <input type="text" v-model="profile.interests" ref="interests" :value="profile.interests" :disabled="!isEditing" :class="{view: !isEditing}"><br>
    <input type="text" v-model="profile.city" ref="patronymic" :value="profile.city" :disabled="!isEditing" :class="{view: !isEditing}"><br>
    <select v-model="profile.gender" ref="gender" :value="profile.gender" :disabled="!isEditing" :class="{view: !isEditing}">
      <option disabled value="">Выберите пол</option>
      <option>MALE</option>
      <option>FEMALE</option>
    </select>
    <span>Пол: {{ profile.gender }}</span><br>
    <br>


    <button @click="isEditing = !isEditing" v-if="!isEditing">
      Edit
    </button>
    <!--button v-if="isEditing" @click="save()" v-else-if="isEditing"-->
    <button v-if="isEditing" @click="save">
      Save
    </button>

    <button v-if="isEditing" @click="cancel">
      Cancel
    </button>

  </div>
</template>

<script>
  import api from "./backend-api";

  export default {
    name: 'user',

    data () {
      return {
        response: [],
        errors: [],
        isEditing: false,
        profile: {
          name: '',
          surname: '',
          patronymic: '',
          gender: '',
          age: 0,
          interests: '',
          city: '',
          id: this.$store.state.personId
        },
        showResponse: false,
        retrievedUser: {},
        showRetrievedUser: false
      }
    },

    /*validations: {
      form: {
        name: { required },
        email: { required, email }
      }
    },*/

    methods: {
      save: function() {
        this.isEditing = false;
/*        this.profile.name = this.$refs['name'].value;
        this.profile.surname = this.$refs['surname'].value;
        this.profile.patronymic = this.$refs['patronymic'].value;
        this.profile.gender = this.$refs['gender'].value;
        this.profile.age = this.$refs['age'].value;
        this.profile.interests = this.$refs['interests'].value;
        this.profile.city = this.$refs['city'].value;*/
        this.savePerson()
      },

      cancel: function() {
        this.isEditing = false;
        this.loadPerson()
      },

      savePerson() {
        api.savePerson(this.profile)
      },

      loadPerson() {
        api.getPersonById(this.profile.id).then(response => {
          this.response = response.data;
          this.profile = response.data
          this.profile.name = response.data.name
          this.profile.surname = response.data.surname
          this.profile.patronymic = response.data.patronymic
          this.profile.city = response.data.city
          this.profile.gender = response.data.gender
          this.profile.interests = response.data.interests
          this.profile.age = response.data.age
          this.profile.id = response.data.id
        })
      },

      // Fetches posts when the component is created.
      createNewUser () {

        api.createUser(this.user).then(response => {
          // JSON responses are automatically parsed.
          this.response = response.data;
          this.user.id = response.data;
          console.log('Created new User with Id ' + response.data);
          this.showResponse = true
        })
                .catch(e => {
                  this.errors.push(e)
                })
      },
      retrieveUser () {
        api.getUser(this.user.id).then(response => {
          // JSON responses are automatically parsed.
          this.retrievedUser = response.data;
          this.showRetrievedUser = true
        })
                .catch(e => {
                  this.errors.push(e)
                })
      }
    },
    created(){
      this.loadPerson()
    },
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
