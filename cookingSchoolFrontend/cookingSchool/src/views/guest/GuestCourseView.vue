<script setup>

import { useCourseStore } from "@/stores/CourseStore.js";
import { onMounted, ref, computed } from "vue";
import { useRouter } from "vue-router";
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
  await courseStore.showCourses();
}

ShowCourses();

</script>

<template>
  <HeaderIcon></HeaderIcon>




  <div>
    <NextCourses/>
  </div>




<!-- bouncing --->
  <v-sheet class=" px-5 py-5" color="secondary">
        <div>
          <h1 class="text-h4 mt-3 text-center text-medium-emphasis">Let´s cook together!</h1>
        </div>
        <v-container class="container2 justify-space-evenly">
          <v-row class="row">
            <v-col class="item">

             <img src="https://cdn.pixabay.com/photo/2017/08/03/21/48/drinks-2578446_640.jpg" class="circle">
                <div class="text-under-circle text-h5">Getränke</div>
                <div class="text-under-circle">Alle Getränke sind inkludiert! Tee, Cafe, Bio Limonaden und Weine</div>

            </v-col>
            <v-col class="item">

              <img src="https://media.gettyimages.com/id/1365546460/de/foto/woman-takes-fresh-organic-vegetables.jpg?s=612x612&w=0&k=20&c=8bSBHcSU4LQ3HLRJ2giUV7s88MafP2GlAVq0QnQ0KB4=" class="circle">
                <div class="text-under-circle text-h5">Qualität</div>
                <div class="text-under-circle">Wir verwenden nur die aller besten biologischen Zutaten</div>

            </v-col>
            <v-col class="item">

              <img src="https://media.gettyimages.com/id/1081422898/de/foto/pfanne-gebraten-ente.jpg?s=612x612&w=0&k=20&c=edZ-HdtxjxI6-uR9XJh5OFiyKgFc9DBoLZJT-5fPH5w=" class="circle">
                <div class="text-under-circle text-h5">Menü</div>
                <div class="text-under-circle">Wir kochen mindestens 4-5 verschiedene Gänge</div>

            </v-col>
            <v-col class="item">

              <img src="https://media.gettyimages.com/id/629355062/de/foto/auf-eine-ern%C3%A4hrung.jpg?s=612x612&w=0&k=20&c=NeGhON_w6H7kEJBJBmr3KpWKDHkT5a_qkn1aoZ5VMXk=" class="circle">
              <div class="text-under-circle text-h5">Einkauf</div>
              <div class="text-under-circle">Wir gehen gemeinsam auf den Markt und besorgen frische Zutaten</div>

            </v-col>
          </v-row>
        </v-container>

  </v-sheet>



  <v-sheet class="">
    <div class="mx-2 mt-5 px-2 py-2">
      <h2 class="text-h4 mt-5">Alle unsere Kurse auf einen Blick:</h2>
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

.container2 {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}
.row {
  justify-content: space-evenly;
}
.item {
  align-content: center;
  justify-self: center;
  text-align: center;
  align-items: center;
  flex-grow: 0;
  flex-basis: 0;
  flex-shrink: 0;
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
  object-fit: cover;
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
  color: #333;
}

.mdi {
  font-size: 70px; /* Passen Sie die Größe nach Bedarf an */
  color: #CB5400;
}
</style>