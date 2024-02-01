<script setup>
import Logo from "@/components/Logo.vue";
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
        src="https://images.pexels.com/photos/8093877/pexels-photo-8093877.jpeg?auto=compress&cs=tinysrgb&w=600"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      > <div class="d-flex fill-height justify-center align-center flex-column">
        <div class="text-h1 font-weight-medium text-shades-white ">
          Cooking
        </div>
        <div class="text-h4 text-white">
          Acedemy
        </div>
      </div>
      </v-carousel-item>
      
      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2016/11/19/12/44/burgers-1839090_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      >
        <div class="d-flex fill-height justify-center align-center flex-column">
          <div class="text-h1 font-weight-medium text-shades-white">
            Explore a World
          </div>
          <div class="text-h1 font-weight-medium text-white">
            of Flavors
          </div>
        </div>
      </v-carousel-item>

      <v-carousel-item
        src="https://www.kuechen-meyer.de/images/headers/kochschule.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      >
        <div class="d-flex fill-height justify-center align-center flex-column">
          <div class="text-h1 font-weight-medium text-shades-white">
            Culinary Creativity
          </div>
          <div class="text-h1 font-weight-medium text-white">
            Starts Here
          </div>
        </div>

      </v-carousel-item>

      <v-carousel-item
        src="https://images.pexels.com/photos/8136881/pexels-photo-8136881.jpeg?auto=compress&cs=tinysrgb&w=600"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      >
        <div class="d-flex fill-height justify-center align-center flex-column">
          <div class="text-h1 font-weight-medium text-shades-white">
            Authentic
          </div>
          <div class="text-h1 font-weight-medium text-white">
            Chefs
          </div>
        </div>
      </v-carousel-item>

      <v-carousel-item
          src="https://images.pexels.com/photos/1398688/pexels-photo-1398688.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

    </v-carousel>
<!-- WELCOME -->

  </div>
  <v-sheet class="container bg-transparent d-flex  flex-column justify-center spruch mt-10 mb-10 elevation-0" width="100%">
    <h2 class="text-h4 text-brown-lighten-5 ">Sinnliches Kochvergnügen erleben – </h2>
      <h2 class="text-h4 text-brown-lighten-5 "> Entdecke die Kunst der kulinarischen Meisterklasse bei Cooking Academy.</h2>

  </v-sheet>

  <!-- first -->

  <v-sheet class="container d-flex  bg-transparent flex-row justify-end typo mt-10 mb-10 elevation-0 align-center " width="100%" >
    <v-card variant="flat" class="mr-3 pa-5 bg-transparent" width="40%"  >
      <v-card-title class="text-h4 mb-2 " varaiant="plain" :style="{ color: __colors.primary }">Unsere Mission</v-card-title>
       <v-card-text class="text-h6 font-weight-light text-brown-lighten-5" >Unsere Mission ist es, die Kunst des Kochens mit Leidenschaft, Kreativität, Nachhaltigkeit und natürlich auch einer guten Portion Spaß zu vermitteln.
         Unabhängig von Ihren kulinarischen Fähigkeiten bieten wir eine Vielzahl von Kursen an, von Anfänger- bis Fortgeschrittenenkursen.
         Lernen Sie alles, was Sie für eine perfekte Küchenperformance benötigen, von grundlegenden Techniken bis zu anspruchsvollen Gerichten.
         Tauchen Sie ein in die Welt der Aromen und entdecken Sie die Freude am Kochen!
       </v-card-text>

      <v-btn class="ms-3" color="primary" rounded="xl" to="/about"  :style="{ color: __colors.primary }" >Erfahre mehr</v-btn>

    </v-card>

    <v-card width="50%"
    ><v-img
        aspect-ratio="4/3"
        cover
        src="https://media.gettyimages.com/id/1059836070/de/foto/aktion-portr%C3%A4t-von-m%C3%A4nnlichen-chef-werfen-zutaten-in-einer-sch%C3%BCssel.jpg?s=612x612&w=0&k=20&c=MpJK_Ws5-YuWUvS3Hyf0jzqlWEw_mof2Rv61OLkSvMQ="
         ></v-img>
    </v-card>
  </v-sheet>



  <v-sheet class="container d-flex  flex-row flex-wrap justify-start  seccard mt-10 elevation-0" width="100%" color="rgb(255, 255, 255, 0.8)">
    <v-card
    ><v-img
        :width="500"
        aspect-ratio="16/9"
        cover
        src="https://media.gettyimages.com/id/1302980333/de/foto/young-chef-learning-stirring-food-in-frying-pan-while-standing-by-colleague-in-kitchen.jpg?s=612x612&w=0&k=20&c=IyZ90eJfwcZDEjyJvJwWfMlI0segeoMI-qhhltJIgCk="
    ></v-img>
    </v-card>

    <v-sheet variant="flat" class="ml-1 bg-transparent  " max-width="600" v-bind="props"  color="rgb(255, 255, 255, 0.8)">
     <h2 class="text-h4 my-4 mx-4 " varaiant="plain" :style="{ color: __colors.primary }">Unsere Kurse</h2>
      <p class="text-h6 font-weight-light typo my-4 mx-4 ">

        Entwickeln Sie Ihre Kochfähigkeiten weiter,
        experimentieren Sie mit verschiedenen Aromen und Texturen,
        und werden Sie ein Meister der Küche,
        der mit Leidenschaft und Kreativität die Vielfalt der kulinarischen Welt erkundet."
      </p>

        <v-btn rounded="xl" color="primary" class="ms-3" to="/courses" varaiant="tonal"  :style="{ color: __colors.primary } ">zu unseren Kursen</v-btn>


    </v-sheet>

  </v-sheet>


  <div>
  <v-sheet class="container d-flex  flex wrap justify-center mt-10 mb-10 elevation-0 align-center bg-transparent" width="100%">

  <h1 class="text-h4 my-3 text-brown-lighten-5"  >Let´s cook together!</h1>
  <v-btn rounded="xl" class="ms-3 my-5" to="/register" color="primary" > Sign in</v-btn>

  <h2 class="text-h4 text-center mb-4" color="primary" ></h2>

  </v-sheet>

<v-sheet class="container d-flex flex-row justify-space-around elevation-0 rounded-0" width="100%">

  <div class="p-3">
    <v-img :width="300"
           src="https://media.gettyimages.com/id/1140201255/de/foto/hands-chopping-vegetables.jpg?s=612x612&w=0&k=20&c=bD4OMeCKVRt2KEz1y1uUY8lLMzCWJO1CAY3Xm9jKaUw="></v-img>
  </div>
  <div class="m-3">
    <v-img :width="300"
           src="https://media.gettyimages.com/id/1302980333/de/foto/young-chef-learning-stirring-food-in-frying-pan-while-standing-by-colleague-in-kitchen.jpg?s=612x612&w=0&k=20&c=IyZ90eJfwcZDEjyJvJwWfMlI0segeoMI-qhhltJIgCk="></v-img>
  </div>
  <div class="m-3">
    <v-img class="p-3" :width="300"
           src="https://media.gettyimages.com/id/1451673778/de/foto/medium-shot-of-smiling-friends-helping-each-other-during-cooking-class.jpg?s=612x612&w=0&k=20&c=7zjOQp0Udd_n0e5dPw6eHLNUtyknHcA3yichA1JY77s="></v-img>
  </div>
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



.background{

  background: rgb(159,163,166);
background: linear-gradient(180deg, rgba(159,163,166,1) 0%, rgba(89,97,106,1) 50%, rgba(16,17,18,1) 100%);
}

.card-background{

}
.carousel-img {
  opacity: 0.4!important;
}
.typo{
  line-height: 110%;
}
.luftig {
  letter-spacing: 0.5em;
}
.button{
  background: rgb(251,231,194);
  background: linear-gradient(82deg, rgba(251,231,194,1) 0%, rgba(207,129,73,1) 32%, rgba(167,57,6,1) 100%);
}
</style>
