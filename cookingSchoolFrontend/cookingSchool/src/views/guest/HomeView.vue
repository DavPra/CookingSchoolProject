<script setup>

import {globalColors as __colors} from "@/assets/colors";
import {ref} from "vue";
const isHovering= ref(false)
</script>

<template >
  <!-- Welcome page for all users with login option -->

<div  class="background">
  <div>
    <v-carousel cycle interval="8000" height="700px">
      <v-carousel-item class="carousel-img"
        src="https://assets2.devourtours.com/wp-content/uploads/Devour-Madrid_Cooking-Class-A_Jun22_1200.jpg"
        cover
      > <div class="d-flex fill-height justify-center align-center flex-column">
        <div class="text-h1 font-weight-bold  text-shades-white ">
          Cooking
        </div>
        <div class="text-h4 text-white">
          Academy
        </div>
      </div>
      </v-carousel-item>
      
      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2016/11/19/12/44/burgers-1839090_960_720.jpg"
        cover
      >
        <div class="d-flex fill-height justify-start align-start flex-column">
          <div class="text-h1 font-weight-bold text-shades-white mt-5 ms-3">
            Explore
          </div>
          <div class="text-h2 font-weight-medium text-white ms-3 ">
            Flavors
          </div>
        </div>
      </v-carousel-item>

      <v-carousel-item
        src="https://www.strasser-steine.at/wp-content/uploads/2017/08/iStock-628428126_ciStock.comJyliana_web-1280x863.jpg"
        cover
      >
      <div class="d-flex fill-height justify-start align-end flex-column">
          <div class="text-h1 font-weight-bold text-shades-white mr-3 mt-5 ">
            Cooking
          </div>
          <div class="text-h2 text-white mr-3">
            Academy
          </div>
        </div>

      </v-carousel-item>

      <v-carousel-item
        src="https://i.shgcdn.com/bb15c3b6-3652-4f81-b776-d592b8c0597c/-/format/auto/-/preview/3000x3000/-/quality/lighter/"
        cover
      >
         <div class="d-flex fill-height justify-end align-start flex-column" >
          <div class="text-h1 font-weight-bold  text-shades-white ms-3">
            Culinary
          </div>
          <div class="text-h2 font-weight-medium text-white ms-4 mb-5">
            Creativity
          </div>
        </div>
      </v-carousel-item>

      <v-carousel-item
          src="https://www.pastaweb.de/wp-content/uploads/2018/11/italienische-kueche.jpg"
        cover
      >
      <div class="d-flex fill-height justify-end align-end flex-column">
          <div class="text-h1 font-weight-bold  text-shades-white mr-3">
            Cooking
          </div>
          <div class="text-h2 text-white mr-4 mb-5">
            Academy
          </div>
        </div>
    </v-carousel-item>

    </v-carousel>
<!-- WELCOME -->

  </div>
  <v-sheet class="container bg-transparent d-flex  flex-column justify-center spruch mt-10 mb-10 elevation-0" width="100%">
    <h2 class="text-h4 font-weight-medium ">Sinnliches Kochvergnügen erleben – </h2>
      <h2 class="text-h4 font-weight-medium "> Entdecke die Kunst der kulinarischen Meisterklasse bei Cooking Academy.</h2>

  </v-sheet>

  <!-- first -->
  <v-sheet class="container d-flex justify-end typo mt-10 mb-10 elevation-0 align-center rounded-0" color="secondary" width="100%">
    <v-row>
      <v-col cols="12" md="6" class="d-flex align-center">
        <v-card variant="flat" class="mr-3 pa-5 bg-transparent align-center">
          <v-card-title class="text-h4 mb-2 text-black" variant="plain" color="primary">Willkommen  bei Cooking Academy</v-card-title>
          <v-card-text class="text-h6 font-weight-light text-black">
            Entdecke die Freude am Kochen in unserer einzigartigen Kochschule. Tauche ein in kulinarische Abenteuer, probiere neue Rezepte und lerne, wie du mit Leidenschaft und Geschicklichkeit köstliche Gerichte zaubern kannst. Unsere erfahrenen Chefköche stehen bereit, um ihre Geheimnisse zu teilen und deine kulinarischen Fähigkeiten auf das nächste Level zu bringen. Mach mit uns gemeinsam die Küche zum Herzstück deiner Kreativität. Starte noch heute dein kulinarisches Abenteuer!
          </v-card-text>
          <v-btn class="ms-3" color="primary" rounded="xl" to="/about">Erfahre mehr</v-btn>
        </v-card>
      </v-col>

      <v-col cols="12" md="6">
        <v-card>
          <v-img
              aspect-ratio="4/3"
              cover
              src="https://media.gettyimages.com/id/1140201428/de/foto/friends-playing-with-chili-peppers-making-moustaches.jpg?s=612x612&w=0&k=20&c=DMpanNC_sLK958Pvti5iDumDITHsmTf0wBZYoVZJ-qc="
          ></v-img>
        </v-card>
      </v-col>
    </v-row>
  </v-sheet>
<!-- second -->
<div>
  <v-sheet class="container d-flex  flex-row flex-wrap justify-start  seccard mt-10 elevation-0 rounded-0 bg-white" width="100%" >
    <v-card
    ><v-img
        :width="600"
        aspect-ratio="4/3"
        cover
        src="https://media.gettyimages.com/id/1302980333/de/foto/young-chef-learning-stirring-food-in-frying-pan-while-standing-by-colleague-in-kitchen.jpg?s=612x612&w=0&k=20&c=IyZ90eJfwcZDEjyJvJwWfMlI0segeoMI-qhhltJIgCk="
    ></v-img>
    </v-card>
    <v-sheet variant="flat" class="ml-10 " max-width="600" v-bind="props"  >
      <v-card-title class="text-h4 m-4 font-weight-medium" variant="plain" color="primary">Unsere Kurse</v-card-title>
      <v-card-text class="text-h6 font-weight-light typo">

        Entwickeln Sie Ihre Kochfähigkeiten weiter,
        experimentieren Sie mit verschiedenen Aromen und Texturen,
        und werden Sie ein Meister der Küche,
        der mit Leidenschaft und Kreativität die Vielfalt der kulinarischen Welt erkundet."
      </v-card-text>

      <v-btn rounded="xl" color="primary" class="ms-3" to="/courses" varaiant="tonal" >zu unseren Kursen</v-btn>
    </v-sheet>
  </v-sheet>


</div>




  <div>
  <v-sheet class="container d-flex  flex wrap justify-center mt-10 mb-2 elevation-0 align-center rounded-0" color="secondary" width="100%">
<v-card class="bg-white text-center mx-auto mt-2 " rounded width="99%" >
  <v-card-title class="text-h4 mp-3 mt-5 font-weight-medium" color="primary" >Bereit, ein Meisterkoch zu werden?</v-card-title>
  <v-card-subtitle class="text-h4 py-3" color="primary" >Registriere dich jetzt und leg los!</v-card-subtitle>
  <v-btn  rounded="xl" class="ms-3 my-5" to="/register" color="primary" > Melde dich an </v-btn>

</v-card>
  <h2 class="text-h4 text-center mb-4" color="primary"></h2>

  </v-sheet>


</div>
</div>
</template>

<style scoped>
.text {
  margin-top: 1.5rem;
  font-size: 1.2rem;
}

.centered-heading {
  font-size: 3rem;
  font-weight: bold;
  color: #CB5400; /* Orange color for the heading */
  margin-bottom: 1rem; /* Center the heading */
  text-align: center;
  white-space: nowrap;
}

.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center; /* Center the content vertically */
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #f8f9fa; /* Light gray background */
  margin: auto; /* Center the container horizontally */
  width: 90%; /* Adjust the width as needed */
}


/*
.background{
 background: rgb(120,134,144);
  background: linear-gradient(180deg, rgba(120,134,144,1) 0%, rgba(74,82,92,1) 50%, rgba(16,17,18,1) 100%);
  background: rgb(159,163,166);
background: linear-gradient(180deg, rgba(159,163,166,1) 0%, rgba(89,97,106,1) 50%, rgba(16,17,18,1) 100%);
}*/

.carousel-img {
  opacity: 0.4!important;
}
.typo{
  line-height: 110%;
}
.luftig {
  letter-spacing: 0.5em;
}
</style>
