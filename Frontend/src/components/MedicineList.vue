<template>
  <div>
    <h1>Medicine List</h1>
    <button @click="navigateToCreateMedicine" class="fancy-button create-button">Create Medicine</button>
    <h2 style="text-align: center; color: #333; margin: 20px 0;">
      Medicine Name - Description - Category
    </h2>
    <ul v-if="medicines.length > 0">
      <li v-for="medicine in medicines" :key="medicine.id">
        {{ medicine.name }} - {{ medicine.description }} - Category: {{ medicine.category.name }}
        <button @click="navigateToUpdateMedicine(medicine.id)" class="fancy-button update-button">Update</button>
        <button @click="deleteMedicine(medicine.id)" class="fancy-button delete-button">Delete</button>
      </li>
    </ul>
    <p v-else>No medicines found</p>
  </div>
</template>


<script>
import axios from 'axios';

export default {
  data() {
    return {
      medicines: []
    };
  },
  created() {
    this.fetchMedicines();
  },
  methods: {
    fetchMedicines() {
      axios.get('http://localhost:8080/medicine')
        .then(response => {
          this.medicines = response.data;
        })
        .catch(error => {
          console.error('Error fetching medicines:', error);
        });
    },
    deleteMedicine(id) {
      axios.delete(`http://localhost:8080/medicine/${id}`)
        .then(() => {
          this.medicines = this.medicines.filter(medicine => medicine.id !== id);
        })
        .catch(error => {
          console.error('Error deleting medicine:', error);
        });
    },
    navigateToCreateMedicine() {
      this.$router.push({ name: 'CreateMedicine' });
    },
    navigateToUpdateMedicine(id) {
  this.$router.push({ name: 'MedicineUpdate', params: { id: id.toString() } });
}

  }
};
</script>
