<template>
  <div class="createMessage">
    <span>Введённое многострочное сообщение:</span>
    <br>
    <textarea v-model="post.content" placeholder="введите несколько строчек"></textarea>
    <br>
    <!--button :disabled="isNewPostDisable" @click="createPost">Отправить новость</button-->
    <button @click="createPost">Отправить новость</button>

    <!--div class="columns medium-3" v-for="article in articles">
      <div class="card">
        <div class="card-divider">
          {{ article.personId }}
        </div>
        <div class="card-section">
          <p>{{ article.content }}.</p>
        </div>
      </div>
    </div>
  </div-->
  <div class="newslist">
    <div class="container">
      <ul class="media-list">
        <li class="media" v-for="article in articles">
          <div class="media-left">
          </div>
          <div class="media-body">
            <h4 class="media-heading"></h4>
            <h5><i>by {{ article.personId }} at {{ article.createdAt }}}</i></h5>
            <p>{{ article.content }}</p>
          </div>
        </li>
      </ul>
    </div>
  </div>
  </div>
</template>

<script>
  import api from './backend-api'

  export default {
    name: 'newslist',
    props: ['source'],
    data () {
      return {
        articles: [],
        post: {
          id: 0,
          content: '',
          createdAt: '',
          personId: this.$store.state.personId
        },
      }
    },
    computed: {
      isNewPostDisable: function() {
        return !(this.post.content.length > 0);
      },
      loadPosts() {
        api.getPostsByPersonId(this.$store.state.personId).then(response => {
          this.articles = response.data
        })
      }
    },
    methods: {

      createPost: function() {
        this.post.createdAt = new Date();
        api.postNews(this.post).then(response => {
          this.post.id = response.data.id
        })

        this.loadPosts()
      },

      loadPosts() {
        api.getPostsByPersonId(this.$store.state.personId).then(response => {
            this.articles = response.data
        })
      }
    },
    created () {
      this.loadPosts()
    }
  }
</script>

<style scoped>
  .media-object {
    width: 128px;
    padding: 10px;
  }
  .media {
    border-top: 1px solid lightgrey;
    padding-top: 20px;
  }
</style>
