<script setup>


import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, reactive, computed} from "vue";
import {useUserStore} from "@/stores/UserStore";

const userStore = useUserStore()
const router = useRouter();
//const editingUser = ref(false);
const user = computed(() => userStore.users.find(u=> u.userId === parseInt(route.params.user)))
const editingUser = ref(null);
const route = useRoute()

const err = false;
const userData = ref({  //ref in reactive fge-ndert geht far nicht
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
    await userStore.showUsers();
    console.log('Component mounted');
  } catch (error) {
    console.error('Error loading users in component mount:', error);
  }
})
const editUser = (user) => {
  console.log('editUser function called userId: ', user)
  console.log(userData.value)
  editingUser.userId = user //user.id,user
  userData.firstname = user.firstname;
  userData.lastname = user.lastname;
  userData.address = user.address;
  userData.mobile = user.mobile;
  userData.email = user.email;
  userData.password = user.password;
  userData.username = user.username;
  userData.admin = user.isAdmin;
  console.log('VIEW editUser function called userId: ', user)
}
async function createOrUpdateUser(){``
  if (user.value === undefined) {
    try {
      await userStore.creatUser(newUser.value)
      router.push('/admin/users')
      console.log('created User')
    } catch (err) {
      if (err.isAxiosError && err.status === 401){
        return err = true
      }
    }
  } else {
    try {
      await userStore.updateUser(user.value.userId, newUser.value)
      //router.push('/home')
      console.log('updateUser done')
    }catch (err) {
      if (err.isAxiosError && err.status === 401 && 403 & 404){
        return err =true
      }
    }
  }
}

/*
async function createOrUpdateUser() {
  console.log('editingUser.value:', editingUser.value);
  console.log('editingUser.value.userId:', editingUser.value.userId);

  console.log('createUser function called')
  if(editingUser.value){
    console.log('editingUser.value:', editingUser.value);
    console.log('editingUser.value.userId:', editingUser.value.userId);

    console.log('1', editingUser.value)
    try{
      console.log('2')
      await userStore.updateUser(editingUser.value.userId, userData.value)
      console.log('3')
      console.log('EdittingUserId',editingUser.value.userId)
    }catch (e){
      console.error(e)
    }

  }else{
    try {
      await userStore.createUser(userData.value);
      console.log('user created:', userData.value);
      await userStore.showUsers();
    } catch (err) {
      if (err.isAxiosError && err.status === 401) {
        console.log(err);
        console.error('Error creating user:', err);
        return (err = true);
      }
    }
  }
}

async function editUser(user){
  console.log('editUser function called userId: ', user)
  console.log(userData.value)
  editingUser.value = true;
  editingUser.userId = user //user.id,user
  userData.firstname = user.firstname;
  userData.lastname = user.lastname;
  userData.address = user.address;
  userData.mobile = user.mobile;
  userData.email = user.email;
  userData.password = user.password;
  userData.username = user.username;
  userData.admin = user.isAdmin;
  console.log('VIEW editUser function called userId: ', user)
}
*/
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
  <!-- Übersicht aller User zur Bearbeitung für den Admin
und zum Upgraden eines Users zum Admin -->
 <!--<div>
    <v-sheet width="300" :elevation="3" rounded class="mx-auto pa-5  ma-4">
      <h2>Create or update a User</h2>
      <v-form @submit.prevent = "createOrUpdateUser">
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

        <v-btn type="submit" block class="mt-2">{{ editingUser ? 'Update' : 'Save' }}</v-btn>



      </v-form>
    </v-sheet>
  </div> -->
  <v-sheet width="300" :elevation="3" rounded class="mx-auto pa-5  ma-4">
    <h2>Create or update a User</h2>
    <v-form @submit.prevent = "createOrUpdateUser">
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
          v-model.number="newUser.mobile"
          label="mobile"
      ></v-text-field>
      <v-text-field
          v-model="newUser.email"
          label="email"
      ></v-text-field>
      <v-text-field
          v-model="newUser.password"
          label="passwort"
      ></v-text-field>
      <v-text-field
          v-model="newUser.username"
          label="username"
      ></v-text-field>
      <v-checkbox
          v-model="newUser.admin"
          label="is admin"
      ></v-checkbox>

      <v-btn type="submit" block class="mt-2">Save</v-btn>



    </v-form>
  </v-sheet>

<v-sheet width="90%" elevation="3" class="mx-auto">
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
        <td><v-btn icon="mdi-pencil" size ="x-small" @click ="editUser(user.userId)"></v-btn></td>
        <td><v-btn icon="mdi-delete" size ="x-small" @click="deleteUser(user.userId)"></v-btn></td>

      </tr>
      </tbody>
    </v-table>
  </div>
</v-sheet>

</template>