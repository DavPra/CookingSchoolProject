<script setup>
import {useUserStore} from "@/stores/UserStore";
import {useCourseStore} from "@/stores/CourseStore";
import {onMounted, ref} from "vue";

const userStore = useUserStore()
const userCount = ref()
const courseStore = useCourseStore()
const courseCount = ref()

onMounted(async () =>{
  await fetchUserCount()
  await fetchCourseCount()
})

const fetchUserCount = async () =>{
  try{
    await userStore.showUsers()
    userCount.value = userStore.users.length
  }catch (error){
    console.error("Fehler beim Laden der Benutzer" , error)
  }
}

const fetchCourseCount = async () => {
  try{
    await courseStore.showCourses()
    courseCount.value = courseStore.courses.length
  }catch(error){
    console.error('Fehler beim Laden der Kurse' ,error)
  }
}


</script>

<template>


  <!--<v-card>
    <v-layout>
      <v-navigation-drawer
          image="https://cdn.vuetifyjs.com/images/backgrounds/bg-2.jpg"
          permanent
          theme="dark"
      >
        <v-list-item title="Cooking Acedmy" subtitle="Vuetify"></v-list-item>
        <v-divider></v-divider>
        <v-list-item
            prepend-avatar="https://randomuser.me/api/portraits/men/85.jpg"
            title="John Leider"
            nav
        ></v-list-item>

        <v-list-item-subtitle>username</v-list-item-subtitle>


        <v-list nav>
          <v-list-item prepend-icon="mdi-email" title="Inbox" value="inbox"></v-list-item>
          <v-list-item prepend-icon="mdi-account-supervisor-circle" title="Supervisors" value="supervisors"></v-list-item>
          <v-list-item prepend-icon="mdi-clock-start" title="Clock-in" value="clockin"></v-list-item>
        </v-list>
      </v-navigation-drawer>
      <v-main style="height: 500px"></v-main>
    </v-layout>
  </v-card> -->


    <v-container class="bg-grey-lighten-5 ">
      <h1>Hallo Admin</h1>
      <v-row no-gutters>
        <v-col>
            <v-card class="pa-2 ma-2 bg-deep-orange-darken-3">
              <v-card-title class="">User Gesamt</v-card-title>
              <v-card-text>
                <v-row>
                  <v-col>
                    <v-icon color="white">mdi-account-multiple</v-icon>
                  </v-col>
                  <v-col>
                    <div>{{ userCount }}</div>
                  </v-col>
                </v-row>
              </v-card-text>
            </v-card>
        </v-col>

        <v-col cols="6">

            <v-card class="pa-2 ma-2 text-white bg-yellow-darken-3">
              <v-card-title>Profil</v-card-title>
              <v-card-subtitle>Username</v-card-subtitle>
            </v-card>

        </v-col>

        <v-col>
          <v-card class="pa-2 ma-2 bg-teal-darken-3 text-white ">
            <v-card-title>Kurse Gesamt</v-card-title>
            <v-card-text>
              <v-row>
                <v-col>
                  <v-icon color="white">mdi-school</v-icon>
                </v-col>
                <v-col>
                  <div>{{ courseCount }}</div>
                </v-col>
              </v-row>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

      <v-row no-gutters>
        <v-col>
          <v-sheet class="pa-2 ma-2">
            .v-col-auto
          </v-sheet>
        </v-col>

        <v-col cols="2">
          <v-sheet class="pa-2 ma-2">
            .v-col-2
          </v-sheet>
        </v-col>

        <v-col>
          <v-sheet class="pa-2 ma-2">
            .v-col-auto
          </v-sheet>
        </v-col>
      </v-row>
    </v-container>



</template>

<style scoped>

</style>