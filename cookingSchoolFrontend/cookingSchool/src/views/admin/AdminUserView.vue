<script setup>


import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, reactive, computed} from "vue";
import {useUserStore} from "@/stores/UserStore";

const userStore = useUserStore()
const router = useRouter();

const user = computed(() => userStore.users.find(u=> u.userId === parseInt(route.params.user)))
const editingUser = ref(null);
const route = useRoute()
const rules = {
  required: value => !!value || 'Field is required',
}

const userErr = ref(false)

const userData = ref({
  firstname: '',
  lastname : '',
  address: '',
  mobile: '',
  email: '',
  password: '',
  username: '',
  admin: true
});
const newUser = ref({
  firstname: user.value?.firstname,
  lastname : user.value?.lastname,
  address: user.value?.address,
  mobile: user.value?.mobile,
  email: user.value?.email,
  password: user.value?.password,
  username: user.value?.username,
  admin: user.value?.admin
})

onMounted(async () => {
  try {
    const users = await userStore.showUsers();
    console.log('user Component mounted', users);
  } catch (error) {
    console.error('Error loading users in component mount:', error);
  }
})

// userData durch newUser ersetzt
const editUser = (user) => {
  console.log('editUser function called userId: ', user)
  console.log(userData.value)
  editingUser.value = user
  newUser.firstname = user.firstname;
  newUser.lastname = user.lastname;
  newUser.address = user.address;
  newUser.mobile = user.mobile;
  newUser.email = user.email;
  newUser.password = user.password;
  newUser.username = user.username;
  newUser.admin = user.isAdmin;

}
async function createOrUpdateUser() {
  try {
    if (editingUser.value === null) {
      await userStore.creatUser(newUser.value);
      console.log('User created successfully');
      newUser.value = {
        firstname: '',
        lastname: '',
        address: '',
        mobile: '',
        email: '',
        password: '',
        username: '',
        admin: true
      };
      await userStore.showUsers()
    } else {
      await userStore.updateUser(editingUser.value.userId, newUser.value);
      console.log('User updated successfully');
      newUser.value = {
        firstname: '',
        lastname: '',
        address: '',
        mobile: '',
        email: '',
        password: '',
        username: '',
        admin: true
      }
      await userStore.showUsers()
    }
   //await router.push('admin/users')
  } catch (error) {
    if(error.response && error.response.status === 409){
      console.log('Email address already exists')
    }
    console.error('Error creating or updating user:', error);
    throw error;
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

async function deleteUser(userId){
  console.log('userId vor dem Funktionsaufruf:', userId);
  try {
    await userStore.deleteUser(userId);
    console.log('user gelöscht, mit der id', userId);
  } catch (err) {
    console.error('Error delete user', err);
  }
}

</script>

<template>
  <v-sheet width="300" :elevation="3" rounded class="mx-auto pa-5  ma-4">
    <h2>Create or update an User</h2>
    <v-form  @submit.prevent = "createOrUpdateUser">
      <v-text-field
          v-model="newUser.firstname"
          label="firstname"
      ></v-text-field>
      <v-text-field
          v-model="newUser.lastname"
          label="lastname"
      ></v-text-field>
      <v-text-field
          v-model="newUser.address"
          label="address"
      ></v-text-field>
      <v-text-field
          v-model.mobile="newUser.mobile"
          label="mobile"


      ></v-text-field>
      <v-text-field
          v-model="newUser.email"
          label="email address"
          type="email"
          placeholder="johndoe@gmail.com"
      ></v-text-field>
      <v-text-field
          v-model="newUser.password"
          label="passwort"
          hint="Passwort muss angegeben werden"

          :rules="[rules.required]"


      ></v-text-field>
      <v-text-field
          v-model="newUser.username"
          label="username"
      ></v-text-field>
      <v-checkbox
          v-model="newUser.admin"
          label="is admin"
      ></v-checkbox>
      <v-alert closable close-label="Close Alert" type="error" title="Error" text="User konnte nicht erstellt werden" v-model="userErr">
      </v-alert>

      <v-btn type="submit" block class="mt-2">{{ editingUser ? 'Update' : 'Save' }}</v-btn>



    </v-form>
  </v-sheet>

  <!-- USER Tabelle -->

<v-sheet width="90%" elevation="3" class="mx-auto">
  <div>
   <!-- <v-text-field v-model="search" label="Search" @input="filterUsers"></v-text-field> -->
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
        <th class="text-left">
          update
        </th>
        <th class="text-left">
          delete
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
        {{ user.isAdmin ? 'Yes' : 'No' }}
        <td><v-btn icon="mdi-pencil" size ="x-small" @click ="editUser(user)"></v-btn></td>
        <td><v-btn icon="mdi-delete" size ="x-small" @click="deleteUser(user.userId)"></v-btn></td>

      </tr>
      </tbody>
    </v-table>
  </div>
</v-sheet>

</template>