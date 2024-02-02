<script setup>
import {useUserStore} from "@/stores/UserStore";
import {useCourseStore} from "@/stores/CourseStore";
import {useUserStoreUpdate} from "@/stores/UserStoreforUpdate.js";
import {onMounted, ref} from "vue";
import Diagramm from "@/components/Diagramm.vue";
import ProfileForm from "@/components/ProfileForm.vue";
import jwtDecode from "jwt-decode";
import axios from "axios";
import {ApiUrl} from "@/helper/ApiHelper";

const userStore = useUserStore()
const updateStore = useUserStoreUpdate()
const userCount = ref()
const courseStore = useCourseStore()
const courseCount = ref()
const nextCourses = ref([]);
const showEditDialog = ref(false);
const isProfileActionInProgress = ref(false);
const userId = jwtDecode(localStorage.getItem("accessToken")).userId;
const user = ref(null);

onMounted(async () => {
  await fetchUserCount()
  await fetchCourseCount()
  await fetchNextCourses()
  await getUserData()
})

const fetchUserCount = async () => {
  try {
    await userStore.showUsers()
    userCount.value = userStore.users.length
  } catch (error) {
    console.error("Fehler beim Laden der Benutzer", error)
  }
}

const fetchCourseCount = async () => {
  try {
    await courseStore.showCourses()
    courseCount.value = courseStore.courses.length
  } catch (error) {
    console.error('Fehler beim Laden der Kurse', error)
  }
}

const fetchNextCourses = async () => {
  try {
    await courseStore.showCourses();
    console.log('Courses in fetchNextCourses:', courseStore.courses);
    const sortedCourses = courseStore.courses.sort(
        (a, b) => new Date(a.startDate) - new Date(b.startDate)
    );
    console.log('Sorted courses:', sortedCourses);
    nextCourses.value = sortedCourses.slice(0, 3);
    console.log('Next courses:', nextCourses.value);
  } catch (error) {
    console.error('Fehler beim Laden der Kurse', error);
  }
};

async function getUserData() {
  try {
    const response = await axios.get(ApiUrl(`/users/${userId}`), {
      headers: {
        'Authorization': 'Bearer ' + window.localStorage.getItem('accessToken')
      }
    });
    user.value = response.data;
  } catch (error) {
    console.error('Error fetching user:', error);
  }
}

async function updateUsers(updatedUserDto) {
  isProfileActionInProgress.value = true;
  try {
    await updateStore.updateUser(userId, updatedUserDto);
    showEditDialog.value = false;
    await getUserData();
  } catch (err) {
    console.error(err);
  } finally {
    isProfileActionInProgress.value = false;
  }
}
</script>

<template>
  <v-container>
    <h1>Hallo {{ user?.firstname }}</h1>
    <v-divider class="my-2"/>
    <v-container class="container bg-brown-lighten-5 rounded">
      <v-col cols="6">
        <v-card class="pa-2 ma-2 text-center">
          <v-icon size="48">mdi-account</v-icon>
          <v-card-title>
            Profil
          </v-card-title>
          <v-divider class="my-2"/>
          <v-card-text>Vorname: {{ user?.firstname }}</v-card-text>
          <v-card-text>Nachname: {{ user?.lastname }}</v-card-text>
          <v-card-text>Email: {{ user?.email }}</v-card-text>
          <v-card-text>Benutzername: {{ user?.username }}</v-card-text>
          <v-card-text>Adresse: {{ user?.address }}</v-card-text>
          <v-divider class="my-2"/>
          <v-card-item>
            <v-btn color="blue-lighten-3" class="mr-4" @click="showEditDialog = true"
                   :disabled="isProfileActionInProgress" rounded>
              Bearbeiten
            </v-btn>
          </v-card-item>
          <v-dialog v-model="showEditDialog" max-width="600">
            <v-card>
              <v-card-title>Profil bearbeiten</v-card-title>
              <v-card-item class="pb-5">
                <ProfileForm :user="user" @save="updateUsers" @abort="showEditDialog = false"
                             :loading="isProfileActionInProgress"/>
              </v-card-item>
            </v-card>
          </v-dialog>
        </v-card>
      </v-col>
      <v-col cols="6">
        <v-row>
          <v-col cols="6">
            <v-card class="pa-2 ma-2 ">
              <v-card-title class="">
                User Gesamt
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-icon color="red">mdi-account-multiple</v-icon>
                  </v-col>
                  <v-col>
                    <div>{{ userCount }}</div>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
          <v-col cols="6">
            <v-card class="pa-2 ma-2 ">
              <v-card-title>
                Kurse Gesamt
              </v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-icon color="orange">mdi-school</v-icon>
                  </v-col>
                  <v-col>
                    <div>{{ courseCount }}</div>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>
        <v-col cols="12">
          <v-card class="pa-2 ma-2 ">
            <v-card-title>
              Zukünftige Kurse:
            </v-card-title>
            <v-card-text>
              <v-list>
                <v-list-item v-for="course in nextCourses" :key="course.courseId">
                  <v-icon color="blue">mdi-calendar</v-icon>
                  <v-list-item-title>{{ course.courseTitle }}</v-list-item-title>
                  <v-list-item-subtitle>
                    {{ new Date(course.startDate).toLocaleDateString() }}
                  </v-list-item-subtitle>
                </v-list-item>
              </v-list>
            </v-card-text>
          </v-card>
        </v-col>
        <v-col cols="12">
          <v-card class="pa-2 ma-2">
            <v-sheet>
              <Diagramm/>
            </v-sheet>
          </v-card>
        </v-col>
      </v-col>
    </v-container>
  </v-container>
</template>

<style scoped>
.background {
  background-image: linear-gradient(90deg, rgba(255, 231, 213, 1) 0%, rgba(226, 117, 27, 1) 50%, rgba(136, 9, 11, 1) 100%);
}

.background1 {
  background-image: linear-gradient(308deg, rgba(255, 231, 213, 1) 0%, rgba(226, 117, 27, 1) 50%, rgba(136, 9, 11, 1) 100%);
}

.container {
  background-color: #FAFAFA;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-around;
}
</style>