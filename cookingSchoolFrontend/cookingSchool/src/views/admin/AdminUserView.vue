<script setup>


import {useRouter} from "vue-router";
import {onMounted, reactive, ref} from "vue";
import {useUserStore} from "@/stores/UserStore";

const userStore = useUserStore()
const router = useRouter();
const err = false;
const userData = ref({ //ref in reactive geändert geht gar nicht
  firstname: '',
  lastname : '',
  address: '',
  mobile: '',
  email: '',
  password: '',
  username: '',
  admin: ''
});
onMounted(async () => {
  try {
    await userStore.showUsers();
    console.log('Component mounted');
  } catch (error) {
    console.error('Error loading users in component mount:', error);
  }
});



async function createUser() {
  console.log('createUser function called');
  try {
    await userStore.createUser(userData.value);
    console.log('User created:', userData.value);
    await userStore.showUsers()
  } catch (err) {
    if (err.isAxiosError && err.status === 401) {
      console.log('Error creating user:', err);
      return (err = true);
    }
  }
}
async function showUsers() {
  try {
    await userStore.showUsers();
    console.log('Users loaded in showUsers:', userStore.users);

  } catch (error) {
    console.error('Error loading users in showUsers:', error);
  }
}

showUsers();
</script>



<template>
  <!-- Übersicht aller User zur Bearbeitung für den Admin
und zum Upgraden eines Users zum Admin -->
  <div>
    <v-sheet width="300" class="mx-auto">
      <h2>Create a new User</h2>
      <v-form @submit.prevent = "createUser">
        <v-text-field
            v-model="userData.firstname"
            label="firstname"
        ></v-text-field>
        <v-text-field
            v-model="userData.lastname"
            label="lastname"
        ></v-text-field>
        <v-text-field
            v-model="userData.address"
            label="address"
        ></v-text-field>
        <v-text-field
            v-model.number="userData.mobile"
            label="mobile"
        ></v-text-field>
        <v-text-field
            v-model="userData.email"
            label="email"
        ></v-text-field>
        <v-text-field
            v-model="userData.password"
            label="passwort"
        ></v-text-field>
        <v-text-field
            v-model="userData.username"
            label="username"
        ></v-text-field>
        <v-checkbox
            v-model="userData.admin"
            label="is admin"
        ></v-checkbox>

        <v-btn type="submit" block class="mt-2">Save</v-btn>
      </v-form>
    </v-sheet>
  </div>
  <div>
    <h1>Users List</h1>
    <ul>
      <li v-for="user in userStore.users" :key="user.userId">
        {{ user.userId }}
        {{ user.firstname }}
        {{ user.lastname }}
        {{user.address}}
        {{user.mobile}}
        {{user.email}}
        {{user.password}}
        {{user.username}}
        {{user.isAdmin}}

      </li>
    </ul>
  </div>

  <div>
    <v-table>
      <thead>
      <tr>
        <th class="text-left">
          user Id
        </th>
        <th class="text-left">
          firstname
        </th>
        <th class="text-left">
          lastname
        </th>
        <th class="text-left">
          address
        </th>
        <th class="text-left">
          mobile
        </th>
        <th class="text-left">
          email
        </th>
        <th class="text-left">
          username
        </th>
        <th class="text-left">
          is Admin
        </th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="user in userStore.users"
          :key="user.userId"
      >
        <td>{{ user.userId }}</td>
        <td>{{ user.firstname }}</td>
        <td>{{ user.lastname }}</td>
        <td>{{ user.address }}</td>
        <td>{{ user.mobile }}</td>
        <td>{{ user.email }}</td>
        <td>{{ user.username }}</td>
        <td>{{ user.isAdmin }}</td>
      </tr>
      </tbody>
    </v-table>
  </div>
</template>