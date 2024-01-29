<script setup>
import Logo from "@/components/Logo.vue";
import {globalColors as __colors} from "@/assets/colors";
</script>

<template >
  <!-- Welcome page for all users with login option -->

<div  class="background">
  <div>
    <v-carousel cycle interval="8000" height="500px">
      <v-carousel-item class="carousel-img"
        src="https://cdn.pixabay.com/photo/2017/03/10/13/57/cooking-2132874_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>
      
      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2016/11/19/12/44/burgers-1839090_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2021/02/08/07/39/chef-5993951_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2014/04/05/11/27/buffet-315691_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

      <v-carousel-item
          src="https://cdn.pixabay.com/photo/2016/11/18/15/31/cooking-1835369_640.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2016/11/21/16/03/campfire-1846142_640.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      >
        <div class="d-flex fill-height justify-center align-center">
          <div class="text-h2 text-white">
            Outdoor Cooking Classes
          </div>
        </div>
      </v-carousel-item>
    </v-carousel>
  </div>
  <div class="container mt-8 bg-brown-lighten-5 ">
    <card>
      <v-card-title><h1 class="centered-heading font-weight-medium text-h2"> Willkommen </h1> </v-card-title>
      <v-card-subtitle class="text-h4 text-center " >bei</v-card-subtitle>
      <v-card-subtitle class="text-h2 text-center" >Cooking Academy</v-card-subtitle>


      <p class="text-center mt-4">
        Entwickeln Sie Ihre Kochfähigkeiten weiter,
        experimentieren Sie mit verschiedenen Aromen und Texturen,
        und werden Sie ein Meister der Küche,
        der mit Leidenschaft und Kreativität die Vielfalt der kulinarischen Welt erkundet.
      </p>
    </card>
  </div>

  <div class="container mt-10 mb-10 card-background">
    <v-row>
      <v-col cols="6">
        <v-card>

          <v-img
            src="https://images.pexels.com/photos/2403392/pexels-photo-2403392.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            contain

          ></v-img>
          <v-card-title class="centered-heading font-weight-medium">Unsere Mission</v-card-title>
  
          <v-card-text class="text-center">
            Unsere Mission ist es, die Kunst des Kochens mit Leidenschaft, Kreativität, Nachhaltigkeit und natürlich auch einer guten Portion Spaß zu vermitteln.
            Unabhängig von Ihren kulinarischen Fähigkeiten bieten wir eine Vielzahl von Kursen an, von Anfänger- bis Fortgeschrittenenkursen.
            Lernen Sie alles, was Sie für eine perfekte Küchenperformance benötigen, von grundlegenden Techniken bis zu anspruchsvollen Gerichten.
            Tauchen Sie ein in die Welt der Aromen und entdecken Sie die Freude am Kochen!
          </v-card-text>

        </v-card>
      </v-col>

      <v-col cols="6">
        <v-card>
          <v-img
            src="https://images.unsplash.com/photo-1556911261-6bd341186b2f?q=80&w=1770&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
            contain
            
          ></v-img>
          <v-card-title class="centered-heading font-weight-medium "   >Unsere Kurse</v-card-title>
  
          <v-card-text class="text-center" >
            Erleben Sie kulinarische Vielfalt in unseren exklusiven Kochkursen. 
            Ob Anfänger oder Profi – unsere Kurse bieten für jeden etwas. 
            Von den Grundlagen bis zu raffinierten Techniken entdecken Sie mit unseren erfahrenen Köchen die Welt des Kochens. 
            Lernen Sie neue Fertigkeiten, genießen Sie inspirierende Stunden und tauchen Sie ein in die Welt der Gastronomie.
            Freuen Sie sich auf kulinarische Entdeckungen und unvergessliche Momente in unserer Kochschule!
          </v-card-text>
          <v-card-actions>
            <v-btn to="/courses" block  variant="tonal"  :style="{ color: __colors.primary }" > Unsere Kurse </v-btn>
          </v-card-actions>
        </v-card>
      </v-col>
    </v-row>
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
  background: rgb(120,134,144);
  background: linear-gradient(180deg, rgba(120,134,144,1) 0%, rgba(74,82,92,1) 50%, rgba(16,17,18,1) 100%);
}
.card-background{

}
.carousel-img {
  opacity: 0.4!important;
}

</style>
