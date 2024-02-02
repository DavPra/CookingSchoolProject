<script setup>
import {useRoute, useRouter} from "vue-router";
import {onMounted, ref, reactive, computed, watch} from "vue";
import {useUserStore} from "@/stores/UserStore";
import {useCourseStore} from "@/stores/CourseStore";
import {useUserStoreUpdate} from "@/stores/UserStoreforUpdate";

const userStore = useUserStore()
const courseStore = useCourseStore()
const router = useRouter();
const user = computed(() => userStore.users.find(u => u.userId === parseInt(route.params.user)))
const editingUser = ref(null);
const route = useRoute()
const errorFeedback = ref('')
const rules = {
  required: value => !!value || 'Field is required',
  min: v => (v && v.length >= 6) || 'Min 6 characters',
  password: v => !v || (v && v.length >= 6) || 'Min 6 characters'
}
const passwort = ref('Passwort')
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
  {title: "User ID", value: "userId"},
  {title: "Vorname", value: "firstname"},
  {title: "Nachname", value: "lastname"},
  {title: "Adresse", value: "address"},
  {title: "Mobile", value: "mobile"},
  {title: "Email", value: "email"},
  {title: "Username", value: "username"},
  {title: "Admin", value: "admin"},
  {title: "Update", value: "update", sortable: false},
  {title: "Löschen", value: "delete", sortable: false},
  {title: "Kurs", value: "course", sortable: false},
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
    //await courseStore.showCourses()
    //courses.value = courseStore.courses
    //console.log('courses Component mounted', courses);
    await loadCourses()
  } catch (error) {
    console.error('Error loading users in component mount:', error);
  }
})

// userData durch newUser ersetzt
const editUser = (user) => {
  console.log('editUser function called userId: ', user);
  console.log(userData.value);
  editingUser.value = user;
  console.log('editingUser.value', editingUser.value);
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
      if (!newUser.value.password) {
        errorFeedback.value ='Bitte geben Sie ein Passwort an';
        return;
      }

      await userStore.creatUser(newUser.value);
      console.log('Benutzer erfolgreich erstellt');
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
      await userStore.showUsers();
    } else {
      console.log('upsihgrehbrthn', editingUser.value.userId, newUser.value);
      await userStore.updateUser(editingUser.value.userId, newUser.value);
      console.log('Benutzer erfolgreich aktualisiert');
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
      await userStore.showUsers();
    }
    errorFeedback.value = '';
  } catch (error) {
    errorFeedback.value = error.response.data.message;
    console.log(error);
    if (error.response && error.response.status === 409) {
      console.log('E-Mail-Adresse existiert bereits');
    }
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


const courses = ref([]);
const isCourseDialogOpen = ref(false)
const selectedCourseId = ref(null)
const courseOptions = ref([])
let userId
const loadCourses = async () => {
  try {
    console.log('Before loading courses');
    await courseStore.showCourses();
    console.log('After loading courses');

    console.log('Courses loaded:', courseStore.courses);

    courseOptions.value = courseStore.courses.map(course => ({
      courseId: course.courseId,
      title: course.courseTitle
    }));

    console.log('courseOptions:', courseOptions.value);
    console.log('courseID for recipes loaded');
  } catch (err) {
    console.error("Error loading courses:", err);
  }
};
const openCourseDialog = () => {
  isCourseDialogOpen.value = true;
  loadCourses()
};
const assignUserToCourse = (usersId) => {
  userId = usersId
  console.log('userId', userId)
  openCourseDialog();
  // selectedCourseId.value = courseId;
  // console.log('courseId', selectedCourseId.value, courseId)

}
const completeAssignment = async () => {
  console.log("userId", userId);
  console.log("courseId", selectedCourseId.value);

  if (selectedCourseId.value !== null) {
    try {
      await userStore.addUserToCourse(userId, selectedCourseId.value);
      closeCourseDialog();
    } catch (error) {
      if (error.response) {
        console.error(error.response.data);
      } else {
        console.error("An unexpected error occurred:", error);
      }
    }
  }
};

const closeCourseDialog = () => {
  isCourseDialogOpen.value = false;
  selectedCourseId.value = null;
};
</script>

<template>
  <div class="background">
    <div class="d-flex flex-wrap justify-center justify-space-evenly">
      <!-- User Form -->
      <div class="mt-5">
        <v-sheet width="400" :elevation="3" rounded class="mx-auto pa-5  ma-4">
          <h2 class="ma-2 ">{{ editingUser ? 'Bearbeite einen User' : 'Erstelle einen neuen User' }}</h2>
          <v-form @submit.prevent="createOrUpdateUser">
            <v-text-field
                variant="outlined"
                v-model="newUser.firstname"
                label="Vorname"
                ref="firstTextField"
            ></v-text-field>
            <v-text-field
                variant="outlined"
                v-model="newUser.lastname"
                label="Nachname"
            ></v-text-field>
            <v-text-field
                variant="outlined"
                v-model="newUser.address"
                label="Adresse"
            ></v-text-field>
            <v-text-field
                variant="outlined"
                v-model.mobile="newUser.mobile"
                label="Mobile"
            ></v-text-field>
            <v-text-field
                variant="outlined"
                v-model="newUser.email"
                label="Email"
                type="email"
                :rules="[rules.required, rules.email]"
                placeholder="johndoe@gmail.com"
                class="mb-1"
            ></v-text-field>
            <v-text-field
                variant="outlined"
                v-model="newUser.password"
                :rules="[rules.password]"
                label="Passwort"
                hint="Mindestens 6 Zeichen"
            ></v-text-field>
            <v-text-field
                variant="outlined"
                v-model="newUser.username"
                label="username"
            ></v-text-field>
            <v-checkbox
                v-model="newUser.admin"
                label="Admin?"
            ></v-checkbox>
            <v-alert v-if="errorFeedback" closable  type="error">{{ errorFeedback }}</v-alert>
            <v-btn rounded type="submit" color="primary" variant="tonal" class="mt-2">{{ editingUser ? 'Bearbeiten' : 'Speichern' }}</v-btn>
          </v-form>
        </v-sheet>
      </div>

      <!-- User Tabelle -->
      <div class="mt-16">

        <v-card  class="mx-auto " tonal title="User Management">
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
                  <v-btn icon="mdi-delete" variant="text" @click="deleteUser(item.userId)">

                  </v-btn>
                </td>
                <td>
                  <v-btn icon="mdi-plus" variant="text" @click="assignUserToCourse(item.userId)">

                  </v-btn>
                </td>
              </tr>
            </template>
          </v-data-table>
        </v-card>
      </div>
    </div>
    <!-- Dialog zum hinzufügen eines Users zu einem Kurs -->
    <v-dialog v-model="isCourseDialogOpen" width="400">
      <v-card>
        <v-card-title>Wähle einen Kurs</v-card-title>

        <v-select variant="outlined" v-model="selectedCourseId" :items="courseOptions" item-value="courseId" label="Select a Course"
                  class="ma-4"></v-select>
        <v-card-actions class="mb-2">
          <v-btn rounded @click="completeAssignment(selectedCourseId)" variant="tonal" color="primary" >Eintragen</v-btn>
          <v-btn rounded @click="closeCourseDialog" variant="outlined" color="primary" >Zurück</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>

</template>
<style scoped>

</style>