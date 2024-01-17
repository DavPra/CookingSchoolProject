<script setup>
  import {ref} from 'vue';
  import {useRouter} from 'vue-router';
  import {useAuthStore} from '@/stores/AuthStore';

  const areCredentialsInvalid = ref(false);
  const isLoginInProgress = ref(false);
  const router = useRouter();
  const authentication = useAuthStore();
  const credentials = ref({
    username: '',
    password: ''
  });

  async function login() {
    try {
      isLoginInProgress.value = true;
      const response = await authentication.login(credentials.value);
      if (response.status === 200) {
        await router.push('/user/courses');
      }
    } catch (error) {
      areCredentialsInvalid.value = true;
      console.error('Fehler beim Login:', error);
    } finally {
      isLoginInProgress.value = false;
    }
  }

  //TODO: Kathy - Fix registration button and link to registration page

</script>

<template>
  <!-- Formular für einen Guest um sich als User einzuloggen, wenn er sich zuvor registriert hat-->
  <v-card-title class="pt-4">Login</v-card-title>
  <v-form @submit.prevent="login">
    <v-card-item>
      <v-alert color="error" v-model="areCredentialsInvalid" closable="true" close-icon="mdi-close" text="Zugangsdaten falsch" class="mb-6"/>
      <v-text-field variant="outlined" required label="Benutzername" v-model="credentials.username" :disabled="isLoginInProgress"/>
      <v-text-field type="password" variant="outlined" required label="Passwort" v-model="credentials.password" :disabled="isLoginInProgress"/>
    </v-card-item>
    <v-card-actions style="margin-left: 10px">
      <v-btn color="primary lighten-2" type="submit" variant="elevated" :loading="isLoginInProgress">Login</v-btn>
    </v-card-actions>
    <v-card-item>
      <div>Noch keinen Account?</div><br>
      <v-btn to="/registration">Hier registrieren</v-btn>
    </v-card-item>
  </v-form>
</template>