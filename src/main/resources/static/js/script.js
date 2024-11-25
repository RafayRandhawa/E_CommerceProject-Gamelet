// Base URL for the API
const API_BASE_URL = 'http://localhost:8085/api/admin'; // Update with your API endpoint

// Function to fetch all products from the API
async function fetchProducts() {
    try {
        const response = await fetch(`${API_BASE_URL}/products`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const products = await response.json();
        console.log('Products:', products); // Handle the products data as needed
        return products;
    } catch (error) {
        console.error('Error fetching products:', error);
    }
}

// Function to fetch a single product by ID
async function fetchProductById(productId) {
    try {
        const response = await fetch(`${API_BASE_URL}/products/${productId}`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const product = await response.json();
        console.log('Product:', product); // Handle the product data as needed
        return product;
    } catch (error) {
        console.error('Error fetching product by ID:', error);
    }
}

// Function to fetch all users from the API
async function fetchUsers() {
    try {
        const response = await fetch(`${API_BASE_URL}/users`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const users = await response.json();
        console.log('Users:', users); // Handle the users data as needed
        return users;
    } catch (error) {
        console.error('Error fetching users:', error);
    }
}

// Function to fetch all orders from the API
async function fetchOrders() {
    try {
        const response = await fetch(`${API_BASE_URL}/orders`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const orders = await response.json();
        console.log('Orders:', orders); // Handle the orders data as needed
        return orders;
    } catch (error) {
        console.error('Error fetching orders:', error);
    }
}

// Function to fetch all reviews from the API
async function fetchReviews() {
    try {
        const response = await fetch(`${API_BASE_URL}/reviews`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const reviews = await response.json();
        console.log('Reviews:', reviews); // Handle the reviews data as needed
        return reviews;
    } catch (error) {
        console.error('Error fetching reviews:', error);
    }
}

// Function to fetch all payments from the API
async function fetchPayments() {
    try {
        const response = await fetch(`${API_BASE_URL}/payments`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const payments = await response.json();
        console.log('Payments:', payments); // Handle the payments data as needed
        return payments;
    } catch (error) {
        console.error('Error fetching payments:', error);
    }
}

// Function to fetch all categories from the API
async function fetchCategories() {
    try {
        const response = await fetch(`${API_BASE_URL}/categories`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const categories = await response.json();
        console.log('Categories:', categories); // Handle the categories data as needed
        return categories;
    } catch (error) {
        console.error('Error fetching categories:', error);
    }
}

// Example usage of the functions
async function fetchAllData() {
    await fetchProducts();
    await fetchUsers();
    await fetchOrders();
    await fetchReviews();
    await fetchPayments();
    await fetchCategories();
}

// Call the function to fetch all data
fetchAllData();