<script setup>
import {ref, onMounted} from 'vue';
import Chart from 'chart.js/auto';

const barChartCanvas = ref(null);

onMounted(() => {
  initializeBarChart();
});

function initializeBarChart() {
  const ctx = barChartCanvas.value.getContext('2d');
  const barChart = new Chart(ctx, {
    type: 'bar',
    data: {
      labels: ['Januar', 'Februar', 'März', 'April', 'Mai'],
      datasets: [{
        label: 'Umsatz',
        data: [65, 59, 80, 81, 56],
        backgroundColor: [
          'rgba(255, 99, 132, 0.2)',
          'rgba(255, 159, 64, 0.2)',
          'rgba(255, 205, 86, 0.2)',
          'rgba(75, 192, 192, 0.2)',
          'rgba(54, 162, 235, 0.2)',
          'rgba(153, 102, 255, 0.2)',
          'rgba(201, 203, 207, 0.2)'
        ],
        borderColor: 'black',
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  });
}
</script>

<template>
  <div>
    <canvas ref="barChartCanvas"></canvas>
  </div>
</template>

<style scoped>

</style>