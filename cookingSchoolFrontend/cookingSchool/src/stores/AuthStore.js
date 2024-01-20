import {defineStore} from 'pinia';
import axios from 'axios';
import {createApiUrl} from '@/helper/ApiHelper';
import jwtDecode from 'jwt-decode';

const accessToken = window.localStorage.getItem('accessToken')

export const useAuthStore = defineStore('authentication', {
    state: () => ({
        user: null,
        accessToken: null
    }),
    actions: {
        async login({username, password}) {
            const response = await axios.post(createApiUrl('authenticate'),{username:username, password:password})
            if(response.status === 403){
                throw new Error('User nicht gefunden')
            }
            const token = response.data
            if(token === '') {
                throw new Error('Token nicht gefunden')
            }
            this.token = token
            console.log(token)
            window.localStorage.setItem('accessToken', token)
            function parseJwt (token) {
                console.log('\n\n\nHallo: ' + jwtDecode(token));

                console.dir(jwtDecode(token));
               return "";
            }
            parseJwt(token);
        },
        async getUser() {
          const config = {
              headers: {
                  Authorization: 'Bearer ' + accessToken
              }
          }
          const response = await axios.get(createApiUrl('/user'), config)
          this.user = response.data.user
        },
        logout() {
            this.user = null
            window.localStorage.clear()
        }
    }
})