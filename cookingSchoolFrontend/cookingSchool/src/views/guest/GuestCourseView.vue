<script setup>

import { useCourseStore } from "@/stores/CourseStore.js";
import { onMounted, ref, computed } from "vue";
import { useRoute, useRouter } from "vue-router";
import CourseCard from "@/components/CourseCard.vue";
import { useAuthStore } from "@/stores/AuthStore.js";
import HeaderIcon from "@/components/HeaderIcon.vue";
import NextCourses from "@/components/NextCourses.vue";

const courseStore = useCourseStore()
const router = useRouter(); // router
const isVisible = ref(false);
const authStore = useAuthStore();


const courses = computed(() => courseStore.courses);


onMounted(() => {
  ShowCourses();
  console.log('mounted');
});


const err = false;


async function ShowCourses() {
  await courseStore.showGuestCourses();
}

ShowCourses();

</script>

<template>
  <HeaderIcon></HeaderIcon>




  <div>
    <NextCourses/>
  </div>



  <div class="d-flex flex-row justify-space-around justify-center flex-wrap">
    <div class="circle-container">
      <div class="circle">
        <v-icon icon="mdi-glass-cocktail" class="mdi"></v-icon>
      </div>
      <div class="text-under-circle text-h5">Getränke</div>
      <div class="text-under-circle">Alle Getränke sind inkludiert</div>
    </div>
    <div class="circle-container">
      <div class="circle ">
        <v-icon icon="mdi-leaf-circle-outline" class="mdi"></v-icon>
      </div>
      <div class="text-under-circle text-h5">Qualität</div>
      <div class="text-under-circle">Wir verwenden nur die besten Zutaten</div>
    </div>
    <div class="circle-container">
      <div class="circle ">
        <v-icon icon="mdi-leaf-circle-outline" class="mdi"></v-icon>
      </div>
      <div class="text-under-circle text-h5">Menü</div>
      <div class="text-under-circle">Wir kochen 4-5 verschiedene Gänge</div>
    </div>
  </div>



  <v-sheet class="">
    <div class="mx-2 my-2 px-2 py-2">
      <h2 class="text-h4 ">Alle unsere Kurse auf einen Blick:</h2>
    </div>

    <v-row class="d-flex ma-2 ">
      <v-col v-for="course in courses" :key="course.courseId" cols="12" sm="6" md="4" lg="3">
        <CourseCard
                
                    :courseTitle="course.courseTitle"
                    :startDate="course.startDate"
                    :description="course.description"
                    :courseId="course.courseId"
                    :teacher="course.teacher"
                    
        />
      </v-col>
    </v-row>
  </v-sheet>
</template>

<style scoped>
.erlebnis {
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.item {
  width: 100%;
}
.circle-container {
  text-align: center;
}
.circle {
  width: 180px;
  height: 180px;
  border-radius: 50%;
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 20px;
  cursor: pointer;
  background-color: #FFE0B2;
}



.circle:hover {
  animation: bounce 0.5s infinite;
}

@keyframes bounce {
  0%, 20%, 50%, 80%, 100% {
    transform: translateY(0);
  }
  40% {
    transform: translateY(-20px);
  }
  60% {
    transform: translateY(-10px);
  }
}

.text-under-circle {
  margin-top: 10px;
  font-size: 14px;
  color: #333;
}
.mdi {
  font-size: 60px; /* Passen Sie die Größe nach Bedarf an */
  color: #f2f2f2;

}

</style>