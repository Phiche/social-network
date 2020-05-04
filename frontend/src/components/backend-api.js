import axios from 'axios'

const AXIOS = axios.create({
  baseURL: `/api`,
  timeout: 1000
});


export default {
    hello() {
        return AXIOS.get(`/hello`);
    },
    getUser(userId) {
        return AXIOS.get(`/user/` + userId);
    },
    getPersonById(personId) {
        return AXIOS.get(`/person/` + personId);
    },
    savePerson(person) {
        let data = JSON.stringify({
            id:  person.id,
            name: person.name,
            surname: person.surname,
            patronymic: person.patronymic,
            gender: person.gender,
            age: person.age,
            interests: person.interests,
            city: person.city
        })

        return AXIOS.post(`/person`, data, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },
    postNews(post) {
        let data = JSON.stringify({
            personId: post.personId,
            content: post.content,
            createdAt: post.createdAt,
        })
        return AXIOS.post(`/post`, data, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },

    getPostsByPersonId(personId) {
        return AXIOS.get(`/post?personId=` + personId);
    },

    getPeople() {
      return AXIOS.get('/people');
    },

    addFriend(parentPersonId, childPersonId, createdAt) {
        let data = JSON.stringify({
            parentPersonId: parentPersonId,
            childPersonId: childPersonId,
            createdAt: createdAt,
        })
        return AXIOS.post(`/relationship`, data, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },

    createUser(user) {
        let data = JSON.stringify({
            password: user.password,
            login: user.username,
            registrationMail: user.mail,
            registrationTelNo: user.telNo,
            registeredAt: user.regDate
        })

        const formData = new FormData();
        formData.append('login', 'username');
        formData.append('password', 'password');
        formData.append('registrationMail', 'mail');
        formData.append('registrationTelNo', 'telNo');

        const payload = {
            login: 'login'
        };

        return AXIOS.post(`/account`, data, {
            headers: {
                'Content-Type': 'application/json',
            }
        });
    },
    getSecured(user, password) {
        return AXIOS.get(`/secured/`,{
            auth: {
                username: user,
                password: password
            }});
    }
}


