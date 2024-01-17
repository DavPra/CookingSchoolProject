import {defineStore} from 'pinia'
import axios from 'axios'
import {createApiUrl} from '@/helper/ApiHelper'

const accessToken = window.localStorage.getItem('accessToken')

export const useAuthStore = defineStore('authentication', {
    state: () => ({
        user: null,
        accessToken: null
    }),
    actions: {
        async login({username, password}) {
            const response = await axios.post(createApiUrl('authenticate'),{username:username, password:password})
            if(response.status !== 200){
                throw new Error('User nicht gefunden')
            }
            const user = response.data.user
            if(!user) {
                throw new Error('User nicht gefunden')
            }
            this.user = user
            this.accessToken = response.data.accessToken
            window.localStorage.setItem('accessToken', this.accessToken)
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