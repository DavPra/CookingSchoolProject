<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, reactive, computed, watch} from "vue";
import {useUserStore} from "@/stores/UserStore";

const userStore = useUserStore()
const router = useRouter();

const user = computed(() => userStore.users.find(u => u.userId === parseInt(route.params.user)))
const editingUser = ref(null);
const route = useRoute()
const show = ref(false)
const rules = {
  required: value => !!value || 'Field is required',
  min: v => (v && v.length >= 6) || 'Min 6 characters'
}
const firstTextField = ref(null);
const shouldFocusFirstTextField = ref(false);
watch(shouldFocusFirstTextField, (newValue) => {
  if (newValue) {
    firstTextField.value.focus();
  }
});
const userErr = ref(false)
const search = ref('');
const headers = [
  { title: "User ID", value: "userId" },
  { title: "Vorname", value: "firstname" },
  { title: "Nachname", value: "lastname" },
  { title: "Adresse", value: "address" },
  { title: "Mobile", value: "mobile" },
  { title: "Email", value: "email" },
  { title: "Username", value: "username" },
  { title: "Admin", value: "admin" },
  { title: "Update", value: "update", sortable: false },
  { title: "Löschen", value: "delete", sortable: false },
];

const userData = ref({
  firstname: '',
  lastname: '',
  address: '',
  mobile: '',
  email: '',
  password: '',
  username: '',
  admin: true
});
const newUser = ref({
  firstname: user.value?.firstname,
  lastname: user.value?.lastname,
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
  console.log('editUser function called userId: ', user);
  console.log(userData.value);
  editingUser.value = user;
  newUser.value = {
    firstname: user.firstname,
    lastname: user.lastname,
    address: user.address,
    mobile: user.mobile,
    email: user.email,
    password: user.password,
    username: user.username,
    admin: user.admin,
  };
  shouldFocusFirstTextField.value = true;
  shouldFocusFirstTextField.value = false;
};

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

  } catch (error) {
    if (error.response && error.response.status === 409) {
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

async function deleteUser(userId) {
  console.log('userId vor dem Funktionsaufruf:', userId);
  try {
    await userStore.deleteUser(userId);
    console.log('user gelöscht, mit der id', userId);
  } catch (err) {
    console.error('Error delete user', err);
  }
}
const matchesSearch = (user) => {
  const searchTerm = search.value.toLowerCase();
  const fullName = `${user.firstname} ${user.lastname}`.toLowerCase();
  return fullName.includes(searchTerm);
};


</script>

<template>
  <v-sheet width="400" :elevation="3" rounded class="mx-auto pa-5  ma-4">
    <h2 class="ma-2">{{ editingUser ? 'Bearbeite einen neuen User' : 'Erstelle einen neuen User' }}</h2>
    <v-form @submit.prevent="createOrUpdateUser">
      <v-text-field
          v-model="newUser.firstname"
          label="Vorname"
          ref="firstTextField"
      ></v-text-field>
      <v-text-field
          v-model="newUser.lastname"
          label="Nachname"
      ></v-text-field>
      <v-text-field
          v-model="newUser.address"
          label="Adresse"
      ></v-text-field>
      <v-text-field
          v-model.mobile="newUser.mobile"
          label="Mobile"


      ></v-text-field>
      <v-text-field
          v-model="newUser.email"
          label="Email"
          type="email"
          :rules="[rules.required, rules.email]"
          placeholder="johndoe@gmail.com"
      ></v-text-field>
      <v-text-field
          v-model="newUser.password"
          :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
          :rules="[rules.min]"
          label="Passwort"
          hint="Mindestens 6 Zeichen"
          @click:append="show = !show"
      ></v-text-field>
      <v-text-field
          v-model="newUser.username"
          label="username"
      ></v-text-field>
      <v-checkbox
          v-model="newUser.admin"
          label="is admin"
      ></v-checkbox>
      <v-alert closable close-label="Close Alert" type="error" title="Error" text="User konnte nicht erstellt werden"
               v-model="userErr">
      </v-alert>

      <v-btn type="submit" block class="mt-2">{{ editingUser ? 'Update' : 'Save' }}</v-btn>


    </v-form>
  </v-sheet>
<!--
  <v-sheet width="90%" elevation="3" class="mx-auto">
    <div>
       <v-text-field v-model="search" label="Search" @input="filterUsers"></v-text-field>
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
          <td>{{ user.admin ? 'Yes' : 'No' }}</td>
          <td>
            <v-btn icon="mdi-pencil" size="x-small" @click="editUser(user)"></v-btn>
          </td>
          <td>
            <v-btn icon="mdi-delete" size="x-small" @click="deleteUser(user.userId)"></v-btn>
          </td>

        </tr>
        </tbody>
      </v-table>
    </div>
  </v-sheet> -->
  <v-card width="90%" class="mx-auto"  tonal title="User Management">
    <template v-slot:text>
      <v-text-field
          v-model="search"
          label="Search"
          prepend-inner-icon="mdi-magnify"
          single-line
          variant="outlined"
          hide-details
      ></v-text-field>
    </template>

    <v-data-table
        :headers="headers"
        :items="userStore.users"
        :search="search"
    >
      <template v-slot:item="{ item }">
        <tr>
          <td>{{ item.userId }}</td>
          <td>{{ item.firstname }}</td>
          <td>{{ item.lastname }}</td>
          <td>{{ item.address }}</td>
          <td>{{ item.mobile }}</td>
          <td>{{ item.email }}</td>
          <td>{{ item.username }}</td>
          <td>{{ item.admin ? 'Yes' : 'No' }}</td>
          <td>
            <v-btn icon="mdi-pencil" variant="text" @click="editUser(item)">

            </v-btn>
          </td>
          <td>
            <v-btn icon = "mdi-delete" variant="text"  @click="deleteUser(item.userId)">

            </v-btn>
          </td>
        </tr>
      </template>
    </v-data-table>
  </v-card>

</template>
