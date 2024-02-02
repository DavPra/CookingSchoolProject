<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from "axios";
import { ApiUrl } from "@/helper/ApiHelper";

const router = useRouter();

const firstname = ref('');
const lastname = ref('');
const address = ref('');
const mobile = ref('');
const email = ref('');
const username = ref('');
const password = ref('');
const isAdmin = ref(false);
const errorFeedback = ref(null);

function validateForm() {
  // Basic form validation
  if (!firstname.value.trim()) {
    errorFeedback.value = 'Vorname ist erforderlich';
    return false;
  }
  if (!lastname.value.trim()) {
    errorFeedback.value = 'Nachname ist erforderlich';
    return false;
  }
  if (!address.value.trim()) {
    errorFeedback.value = 'Adresse ist erforderlich';
    return false;
  }
  if (!mobile.value.trim()) {
    errorFeedback.value = 'Handynummer ist erforderlich';
    return false;
  }
  if (!email.value.trim()) {
    errorFeedback.value = 'E-Mail ist erforderlich';
    return false;
  }
  if (!/^\S+@\S+\.\S+$/.test(email.value.trim())) {
    errorFeedback.value = 'Ungültige E-Mail-Adresse';
    return false;
  }
  if (!username.value.trim()) {
    errorFeedback.value = 'Benutzername ist erforderlich';
    return false;
  }
  if (!password.value.trim()) {
    errorFeedback.value = 'Passwort ist erforderlich';
    return false;
  }

  // If all validations pass
  return true;
}

async function registerUser() {
  try {
    // Validate the form
    if (validateForm()) {
      // Proceed with registration if validation passes
      const userData = {
        firstname: firstname.value,
        lastname: lastname.value,
        address: address.value,
        mobile: mobile.value,
        email: email.value,
        username: username.value,
        password: password.value,
        isAdmin: isAdmin.value
      };

      try {
        const response = await axios.post(ApiUrl('/registration'), userData, {
          headers: {
            'Content-Type': 'application/json'
          }
        });

        if (response.status === 200) {
          console.log('User created:', userData);
          await router.push('/login');
        }
      } catch (error) {
        console.dir(error);
        console.error('Ein Fehler ist aufgetreten:', error);
        errorFeedback.value = error.response.data.message;
      }
    }
  } catch (error) {
    console.error('Fehler beim Hinzufügen des Benutzers:', error);
  }
}
</script>

<template>
  <!-- Formular für einen neuen User um sich zu registrieren -->
<v-container>
  <v-container class="container">
    <v-col cols="12">
    <v-card class="pa-3 ma-2">
      <v-card-title>
        Legen Sie einen neuen Account an:
      </v-card-title>
      <v-divider class="my-5"/>
    <v-form @submit.prevent="registerUser">
      <v-row>
        <v-col cols="12" md="6">
          <v-text-field v-model="firstname" label="Vorname" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="lastname" label="Nachname" required></v-text-field>
        </v-col>
        <v-col cols="12">
          <v-text-field v-model="address" label="Adresse" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="mobile" label="Handynummer" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="email" label="E-Mail" type="email" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="username" label="Benutzername" required></v-text-field>
        </v-col>
        <v-col cols="12" md="6">
          <v-text-field v-model="password" label="Passwort" type="password" required></v-text-field>
        </v-col>
        <v-col cols="12" v-if="errorFeedback">
          <v-alert type="error">{{ errorFeedback }}</v-alert>
        </v-col>
        <v-col cols="12" class="text-center">
          <v-btn type="submit" color="primary">Registrieren</v-btn>
        </v-col>
      </v-row>
    </v-form>
    </v-card>
    </v-col>
  </v-container>
</v-container>
</template>

<style scoped>
.container {
background-color: #EFEBE9;
display: flex;
flex-flow: row wrap;
justify-content: space-around;
}
</style>