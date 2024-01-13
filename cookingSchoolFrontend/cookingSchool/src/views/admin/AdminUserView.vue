<script setup>


import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import {useUserStore} from "@/stores/UserStore";

const userStore = useUserStore()
const router = useRouter();

onMounted(() => {
  showUsers();
  console.log('mounted');
});


const err = false;
const data = ref({
  firstname: '',
  lastname : '',
  address: '',
  mobile: '',
  email: '',
  password: '',
  username: '',
  admin: ''
});

async function createUser() {
  console.log('createUser function called');
  try {
    await userStore.createUser(data.value);
    await router.push('/admin');
  } catch (err) {
    if (err.isAxiosError && err.status === 401) {
      console.log(err);
      return (err = true);
    }
  }
}

async function showUsers() {
  await userStore.showUsers();
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
            v-model="data.firstname"
            label="firstname"
        ></v-text-field>
        <v-text-field
            v-model="data.lastname"
            label="lastname"
        ></v-text-field>
        <v-text-field
            v-model="data.address"
            label="address"
        ></v-text-field>
        <v-text-field
            v-model.number="data.mobile"
            label="mobile"
        ></v-text-field>
        <v-text-field
            v-model="data.email"
            label="email"
        ></v-text-field>
        <v-text-field
            v-model="data.password"
            label="passwort"
        ></v-text-field>
        <v-text-field
            v-model="data.username"
            label="username"
        ></v-text-field>
        <v-checkbox
            v-model="data.admin"
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