<script setup>
import {useCourseStore} from "@/stores/CourseStore";
import {ref} from "vue";
const courseStore = useCourseStore()
const course = defineProps(['courseTitle','startDate','description', 'courseId','teacher'])

/*async function updateCourse(courseId){
  console.log('updateCourse function called')
  await courseStore.updateCourse(courseId)
  // await courseStore.showCourses()
}*/

async function deleteCourse(courseId){

  console.log('Deleted')
  await courseStore.deleteCourse(courseId)
}
const show = ref(false)
</script>

<template>


        <v-card>
          <v-img
              cover
              height="250"
              src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
          ></v-img>

          <v-card-title>{{ course.courseTitle }}</v-card-title>
          <v-card-subtitle>{{ course.startDate }}</v-card-subtitle>
          <v-card-text>{{ course.teacher }}</v-card-text>
          <v-card-actions>
            <v-btn
                color="orange-lighten-2"
                variant="text"
            >
              Details
            </v-btn>

            <v-spacer></v-spacer>

            <v-btn
                :icon="show ? 'mdi-chevron-up' : 'mdi-chevron-down'"
                @click="show = !show"
            ></v-btn>
          </v-card-actions>

          <v-expand-transition>
            <div v-show="show">
              <v-divider></v-divider>

              <v-card-text>
                {{course.description}}
                </v-card-text>
            </div>
          </v-expand-transition>
          <v-card-actions>


            <v-btn @click="showForm" icon>
              <v-icon>mdi-pencil</v-icon>
            </v-btn>
            <v-btn @click="deleteCourse(course.courseId)" icon>
              <v-icon>mdi-delete</v-icon>
            </v-btn>
          </v-card-actions>
        </v-card>

</template>

<style scoped>

</style>
