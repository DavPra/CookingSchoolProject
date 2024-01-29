<script setup>
import {useUserStore} from "@/stores/UserStore";
import {useCourseStore} from "@/stores/CourseStore";
import {onMounted, ref} from "vue";
import Test from "@/components/Diagramm.vue";
import DoughnutChart from "@/components/DoughnutChart.vue";

const userStore = useUserStore()
const userCount = ref()
const courseStore = useCourseStore()
const courseCount = ref()
const nextCourses = ref([]);


onMounted(async () =>{
  await fetchUserCount()
  await fetchCourseCount()
  await fetchNextCourses()
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


</script>

<template>

    <v-container class="bg-grey-lighten-5" >
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

<Test/>

          </v-sheet>
        </v-col>

        <v-col cols="2">
          <v-sheet class="pa-2 ma-2 bg-grey-darken-3">



          </v-sheet>
        </v-col>
        <v-col>
            <v-card class="pa-2 ma-2 text-white bg-teal-darken-3">
              <v-card-title>Upcomming Courses:</v-card-title>
              <v-card-text>
                <v-list class="text-white bg-teal-darken-3">
                  <v-list-item v-for="course in nextCourses" :key="course.courseId">
                      <v-icon>mdi-calendar</v-icon>
                    <v-list-item-title>{{ course.courseTitle }}</v-list-item-title>
                    <v-list-item-subtitle>
                      {{ new Date(course.startDate).toLocaleDateString() }}
                    </v-list-item-subtitle>
                  </v-list-item>
                </v-list>
              </v-card-text>
            </v-card>
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