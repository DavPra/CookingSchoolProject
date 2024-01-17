import { defineStore } from "pinia";
import axios from 'axios';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        token: null,
        user: null}),

    actions: {
        async login(credentials) {
            const loginData = {
                email: credentials.email,
                password: credentials.password,
            };
            const response = await axios.post ('https://codersbay.a-scho-wurscht.at/api/auth/login', loginData)
            this.token = response.data.accessToken;
            window.localStorage.setItem('token', this.token);
        },
        async getUser(){
            const config = {
                headers: {
                    Authorization: 'Bearer '+ window.localStorage.getItem('token')
                }}
            const userResponse= await axios.get('https://codersbay.a-scho-wurscht.at/api/auth', config)
            this.user = userResponse.data.user
            console.log(this.user)
        },
        logout(){
            this.user = null
            window.localStorage.clear()
        }
    }
})