<script setup>
import { useRouter } from 'vue-router';
import { ref } from 'vue';
import axios from "axios";
import { createApiUrl } from "@/helper/ApiHelper";

const router = useRouter();

//TODO: Kathy - get user data from backend
const firstname = ref('');
const lastname = ref('');
const address = ref('');
const mobile = ref('');
const email = ref('');
const username = ref('');
const password = ref('');
const isAdmin = ref(false);

async function updateUser() {
  try {
    //TODO: Kathy - save user data into backend
    const userData = {
      firstname: firstname.value,
      lastname: lastname.value,
      address: address.value,
      mobile: mobile.value,
      email: email.value,
      username: username.value,
      password: password.value,
      isAdmin: isAdmin.value  //has to be false
    };
    console.log(userData);
    try {
      //TODO: Kathy - get userID from accessToken
      const userID = localStorage.getItem('userID');
      //TODO: Kathy - implement userId into path
      const response = await axios.put(createApiUrl('/users/:userId'), userData, {
        headers: {
          //TODO: Kathy - put accessToken into header
          'Content-Type': 'application/json'
        }
      });
      if (response.status === 200) {
        console.log('User updated:', userData);
        await router.push('/user/profile');
      }
    } catch (error) {
      console.error('Fehler beim Updaten des Users:', error);
    }
  } catch (error) {
    console.error('Fehler beim Updaten:', error);
  }
}

//TODO: Kathy - v-text-field should be filled with user data, password should be hidden characters
</script>

<template>
  <!-- User kann sich seine Daten ansehen/bearbeiten und welche Kurse er bereits gebucht hat -->

  <v-container>
    <v-form @submit.prevent="updateUser">
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
        <v-col cols="12">
          <v-btn type="submit" color="primary">Änderungen speichern</v-btn>
        </v-col>
      </v-row>
    </v-form>
  </v-container>
</template>
