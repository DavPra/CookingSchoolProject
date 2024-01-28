<script setup>
import ProfileForm from '@/components/ProfileForm.vue';
import { ref, onBeforeMount, onMounted } from 'vue';
import { useUserStoreUpdate } from '@/stores/UserStoreforUpdate';
import jwtDecode from 'jwt-decode';
import axios from 'axios';

const userStore = useUserStoreUpdate();

const showEditDialog = ref(false);
const isProfileActionInProgress = ref(false);
const userId = jwtDecode(localStorage.getItem("accessToken")).userId;

let user = ref(null);

onBeforeMount(async () => {
  await getUserData();
});

async function getUserData() {
  try {
    const response = await axios.get(`http://localhost:8082/users/${userId}`);
    user.value = response.data;
  } catch (error) {
    console.error('Error fetching user:', error);
  }
}

async function updateUsers(updatedUserDto) {
  isProfileActionInProgress.value = true;
  try {
    await userStore.updateUser(userId, updatedUserDto);
    showEditDialog.value = false;
    await getUserData(); // Update user data after the update
  } catch (err) {
    console.error(err);
  } finally {
    isProfileActionInProgress.value = false;
  }
}

</script>

<template>
  <!-- User kann sich seine Daten ansehen/bearbeiten/löschen -->

  <v-container>
    <v-card>
      <v-card-title>{{ user?.firstname }}'s Profil:</v-card-title>
      <v-divider class="my-5" />
      <v-card-item>
        <v-card-text>Vorname: {{ user?.firstname }}</v-card-text>
      </v-card-item>
      <v-card-item>
        <v-card-text>Nachname: {{ user?.lastname }}</v-card-text>
      </v-card-item>
      <v-card-item>
        <v-card-text>Email: {{ user?.email }}</v-card-text>
      </v-card-item>
      <v-card-item>
        <v-card-text>Benutzername: {{ user?.username }}</v-card-text>
      </v-card-item>
      <v-card-item>
        <v-card-text>Adresse: {{ user?.address }}</v-card-text>
      </v-card-item>
      <v-divider class="my-2"/>
      <v-card-item>
        <v-btn color="primary" class="mr-4" @click="showEditDialog = true" :disabled="isProfileActionInProgress">
          Bearbeiten
        </v-btn>
      </v-card-item>
      <v-dialog v-model="showEditDialog" max-width="600">
        <v-card>
          <v-card-title>Profil bearbeiten</v-card-title>
          <v-card-item class="pb-5">
            <ProfileForm :user="user" @save="updateUsers" @abort="showEditDialog = false" :loading="isProfileActionInProgress"/>
          </v-card-item>
        </v-card>
      </v-dialog>
    </v-card>
  </v-container>

</template>