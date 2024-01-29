<script setup>
import Logo from "@/components/Logo.vue";
</script>

<template>
  <!-- Welcome page for all users with login option -->
  <div class="container">
    <card>
      <v-card-title><h1 class="centered-heading font-weight-medium text-h2"> Welcome </h1> </v-card-title>
      <v-card-subtitle>bei</v-card-subtitle>


      <p class="text-center">
        Entwickeln Sie Ihre Kochfähigkeiten weiter, 
        experimentieren Sie mit verschiedenen Aromen und Texturen, 
        und werden Sie ein Meister der Küche, 
        der mit Leidenschaft und Kreativität die Vielfalt der kulinarischen Welt erkundet. 
      </p>
    </card>
  </div>

  <div>
    <v-carousel cycle interval="8000" height="500px">
      <v-carousel-item
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
        src="https://cdn.pixabay.com/photo/2016/03/27/21/34/restaurant-1284351_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2023/01/09/10/56/meal-7707134_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>

      <v-carousel-item
        src="https://cdn.pixabay.com/photo/2023/05/30/06/16/vegetable-pan-8027678_960_720.jpg"
        reverse-transition="fade-transition"
        transition="fade-transition"
        cover
      ></v-carousel-item>
    </v-carousel>
  </div>

 
  <div class="container">
    <v-row>
      <v-col cols="6">
        <v-card>

          <v-img
            src="https://images.pexels.com/photos/2403392/pexels-photo-2403392.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            contain

          ></v-img>
          <v-card-title class="centered-heading">Unsere Mission</v-card-title>
  
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
            src="https://images.pexels.com/photos/691114/pexels-photo-691114.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
            contain
            
          ></v-img>
          <v-card-title class="centered-heading">Unsere Kurse</v-card-title>
  
          <v-card-text class="text-center">
            Erleben Sie kulinarische Vielfalt in unseren exklusiven Kochkursen. 
            Ob Anfänger oder Profi – unsere Kurse bieten für jeden etwas. 
            Von den Grundlagen bis zu raffinierten Techniken entdecken Sie mit unseren erfahrenen Köchen die Welt des Kochens. 
            Lernen Sie neue Fertigkeiten, genießen Sie inspirierende Stunden und tauchen Sie ein in die Welt der Gastronomie.
            Freuen Sie sich auf kulinarische Entdeckungen und unvergessliche Momente in unserer Kochschule!
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>
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

</style>
