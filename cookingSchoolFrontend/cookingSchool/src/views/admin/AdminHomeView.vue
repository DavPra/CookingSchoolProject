<script setup>
import {useUserStore} from "@/stores/UserStore";
import {useCourseStore} from "@/stores/CourseStore";
import {onMounted, ref} from "vue";

const userStore = useUserStore()
const userCount = ref()
const courseStore = useCourseStore()
const courseCount = ref()
const nextCourses = ref([]);


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
const fetchNextCourses = async () => {
  try {
    await courseStore.showCourses();
    const sortedCourses = courseStore.courses.sort(
        (a, b) => new Date(a.startDate) - new Date(b.startDate)
    );
    nextCourses.value = sortedCourses.slice(0, 3);
  } catch (error) {
    console.error("Fehler beim Laden der Kurse", error);
  }
};

</script>

<template>

    <v-container class="bg-grey-lighten-5 ">
      <h1>Hallo Admin</h1>
      <v-row no-gutters>
        <v-col>
            <v-card class="pa-2 ma-2 bg-amber-darken-2 text-white">
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

            <v-card class="pa-2 ma-2 text-white bg-light-blue-darken-1 text-center">

              <v-icon size="48">mdi-account</v-icon>
              <v-card-title>Profil</v-card-title>

              <v-card-text>Username</v-card-text>


            </v-card>

        </v-col>

        <v-col>
          <v-card class="pa-2 ma-2 bg-deep-orange-darken-4 text-white ">
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
          <v-sheet class="pa-2 ma-2 bg-grey-darken-3">
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
.background{
  background-image: linear-gradient(90deg, rgba(255,231,213,1) 0%, rgba(226,117,27,1) 50%, rgba(136,9,11,1) 100%);
}
.background1{
  background-image: linear-gradient(308deg, rgba(255,231,213,1) 0%, rgba(226,117,27,1) 50%, rgba(136,9,11,1) 100%);
}
</style>