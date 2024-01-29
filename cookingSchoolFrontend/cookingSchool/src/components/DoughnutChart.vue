<script setup>
import { ref, onMounted } from 'vue';
import Chart from 'chart.js/auto';

const doughnutChartCanvas = ref(null);

onMounted(() => {
  initializeDoughnutChart();
});

function initializeDoughnutChart() {
  const ctx = doughnutChartCanvas.value.getContext('2d');
  const doughnutChart = new Chart(ctx, {
    type: 'doughnut',
    data: {
      labels: ['Red', 'Blue', 'Yellow'],
      datasets: [{
        data: [300, 50, 100],
        backgroundColor: ['red', 'blue', 'yellow'],
        borderColor: 'black', // Border-Farbe hier einstellen
        borderWidth: 1
      }]
    },
    options: {
      cutout: '70%', // Loch in der Mitte des Doughnut-Diagramms (70% für einen Ringeffekt)
      responsive: true,
      maintainAspectRatio: false
    }
  });
}
</script>

<template>
  <div>
    <canvas ref="doughnutChartCanvas"></canvas>
  </div>
</template>

<style scoped>

</style>