<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'
import {useAuthenticationStore} from '@/stores/authenticationStore'

const areCredentialsInvalid = ref(false)
const isLoginInProgress = ref(false)
const router = useRouter()
const authentication = useAuthenticationStore()
const credentials = ref({
  email: '',
  password: ''
})

async function login() {
  isLoginInProgress.value = true
  try {
    await authentication.login(credentials.value)
    await router.push('dashboard')
  } catch (err) {
    areCredentialsInvalid.value = true
  } finally {
    isLoginInProgress.value = false
  }
}
</script>

<template>
  <v-card-title class="pt-4">Login</v-card-title>
  <form @submit.prevent="login" style="margin-bottom: 10px;">
    <v-card-item>
      <v-alert color="error" v-model="areCredentialsInvalid" closable close-icon="mdi-close" text="Zugangsdaten falsch" class="mb-6"/>
      <v-text-field variant="outlined" required label="E-Mail" v-model="credentials.email" :disabled="isLoginInProgress"/>
      <v-text-field type="password" variant="outlined" required label="Passwort" v-model="credentials.password" :disabled="isLoginInProgress"/>
    </v-card-item>
    <v-card-action style="margin: 120px">
      <v-btn color="primary lighten-2" type="submit" variant="elevated" :loading="isLoginInProgress">Login</v-btn>
    </v-card-action>
  </form>
</template>

<style scoped></style>
