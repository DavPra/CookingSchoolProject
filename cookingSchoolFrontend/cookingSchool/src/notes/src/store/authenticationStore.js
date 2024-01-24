import { defineStore } from 'pinia'
import axios from 'axios'
import { createApiUrl } from '@/helper/ApiHelper'

/**
 * Das ist bitte nicht als seriöser Login zu verstehen!
 * Macht man eigentlich unter keinen Umständen so und dient jetzt nur als Mock-Login, damit das Beispiel mit Child Routes Sinn macht 🤡🤡🤡
 */
export const useAuthenticationStore = defineStore('authentication', {
  state: () => ({
    user: null
  }),
  actions: {
    async login({ username, password }) {
      const response = await axios.get(createApiUrl('/users'))
      const user = response.data.find(user => user.username === username && user.password === password)
      if(!user) {
        throw new Error('Kein User gefunden')
      }
      this.user = user
    },
    async register(registrationDto) {
      await axios.post(createApiUrl('/users'), registrationDto)
    }
  }
})
