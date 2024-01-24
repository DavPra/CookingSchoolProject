<template>
  <VCardTitle class="pt-4">Registrieren</VCardTitle>
  <form @submit.prevent="register">
    <VCardItem>
      <VAlert v-model="doPasswordsMismatch" closable close-icon="mdi-close" color="error" text="Passwörter stimmen nicht überein" class="mb-6"/>
      <VTextField variant="outlined" required label="Benutzername" v-model="registrationDto.username" :disabled="isRegistrationInProgress"/>
      <VTextField type="password" variant="outlined" required label="Fake Passwort" v-model="registrationDto.password" :disabled="isRegistrationInProgress"/>
      <VTextField type="password" variant="outlined" required label="Fake Passwort Wiederholen" v-model="passwordRepeat" persistentHint hint="Keine echten Passwörter angeben!" :disabled="isRegistrationInProgress"/>
    </VCardItem>
    <VCardActions>
      <VBtn color="primary" type="submit" variant="elevated" :loading="isRegistrationInProgress">Registrieren</VBtn>
      <VBtn color="secondary" to="/" :disabled="isRegistrationInProgress">Zurück zu login</VBtn>
    </VCardActions>
  </form>
</template>

<script setup>
  import { ref } from 'vue'
  import { useRouter } from 'vue-router'
  import { useAuthenticationStore } from '@/store/authenticationStore'

  const registrationDto = ref({
    username: '',
    password: ''
  })
  const passwordRepeat = ref('')
  const doPasswordsMismatch = ref(false)
  const router = useRouter()
  const authenticationStore = useAuthenticationStore()
  const isRegistrationInProgress = ref(false)

  async function register() {
    doPasswordsMismatch.value = registrationDto.value.password !== passwordRepeat.value
    if(doPasswordsMismatch.value) {
      return
    }
    isRegistrationInProgress.value = true
    await authenticationStore.register(registrationDto.value)
    await router.push('/')
  }
</script>
