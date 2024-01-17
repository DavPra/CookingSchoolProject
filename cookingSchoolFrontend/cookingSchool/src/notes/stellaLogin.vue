<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import { useAuthStore } from '../stores/authStore'
import SvgIcon from '@jamescoyle/vue-icon'
import { mdiAccount, mdiArrowUpThick } from '@mdi/js'
import axios, { isAxiosError } from 'axios'

const auth = useAuthStore()
const loginErr = ref(false)
const router = useRouter()
const credentials = ref({
  email: '',
  password: ''
})

async function login(){
  try{
    await auth.login(credentials.value)
    router.push('/home')
  }catch(err) {
    if (err.isAxiosError && err.response.status === 401) {
      return loginErr.value = true
    }
    console.error(err)
  }
}
</script>

<template>
  <div class="background">
    <v-sheet class="d-flex justify-center flex-wrap flex-row bg-transparent h-screen mx-10" >
      <v-sheet id="col_left" class=" align-center bg-transparent my-auto mx-auto">
        <div class="text-h1 text-white text-center heading ">Weakly</div>
        <div class="text-h3 text-white text-center heading ">Planner</div>
        <v-progress-linear class="mt-4" color="white" indeterminate :height="7"></v-progress-linear>
        <p class="text-white mt-2">Website im Aufbau</p>
      </v-sheet>

      <v-sheet border:height="950" :width="600" class="d-flex flex-column justify-center mx-auto  my-auto text-black backgroundcard rounded-sm" >
        <h1 class=" pa-3 text-center text-h4 text-teal-darken-3  ">
          <!-- Login -->
          <br>
          <v-icon icon="mdi-account" size="x-large" class="text-h1 text-teal-darken-4 text-center "></v-icon> </h1>

        <v-form validate-on="submit" @submit.prevent="login">
          <v-text-field v-model="credentials.email" label="Enter your E-mail" class=" mt-5 bg-white px-4" variant="outlined"></v-text-field>
          <v-text-field v-model="credentials.password" type="password" label="Passwort" class=" bg-white mt-3 px-4" variant="outlined"></v-text-field>
          <v-alert closable close-label="Close Alert" class="px-4" type="error" title="Login Fehler" text="Emailadress or password is incorrect" v-model="loginErr">
          </v-alert>
          <v-btn type="submit"  color="teal-darken-3"   class=" ml-4 align-center text-button mb-4" size="large" >Login</v-btn>
        </v-form>
      </v-sheet>
    </v-sheet>
  </div>
</template>