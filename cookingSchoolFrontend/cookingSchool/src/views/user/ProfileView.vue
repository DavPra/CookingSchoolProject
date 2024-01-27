<script setup>
import ProfileForm from '@/components/ProfileForm.vue';
import { ref, onBeforeMount, onMounted } from 'vue';
import { useUserStore } from '@/stores/userStore.js';
import jwtDecode from 'jwt-decode';
import axios from 'axios';

const userStore = useUserStore();

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
    <div>
      <div>
        <v-btn color="primary" class="mr-4" @click="showEditDialog = true" :disabled="isProfileActionInProgress">
          Bearbeiten
        </v-btn>
      </div>
      <v-divider class="my-5" />
      <p>Vorname: <strong>{{ user?.firstname }}</strong></p>
      <p>Nachname: <strong>{{ user?.lastname }}</strong></p>
      <p>Email: <strong>{{ user?.email }}</strong></p>
      <p>Username: <strong>{{ user?.username }}</strong></p>
      <p>Adresse: <strong>{{ user?.address }}</strong></p>
      <v-dialog v-model="showEditDialog" max-width="600">
        <v-card>
          <v-card-title>Profil bearbeiten</v-card-title>
          <v-card-item class="pb-5">
            <ProfileForm :user="user" @save="updateUsers" @abort="showEditDialog = false" :loading="isProfileActionInProgress"/>
          </v-card-item>
        </v-card>
      </v-dialog>
    </div>
  </v-container>
</template>
