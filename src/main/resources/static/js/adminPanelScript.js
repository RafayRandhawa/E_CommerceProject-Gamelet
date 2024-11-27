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
        return categories; // Return the fetched categories
    } catch (error) {
        console.error('Error fetching categories:', error);
        return null; // Return null in case of an error
    }
}
async function fetchCategoryById(category_id){
    try{
        const response = await fetch(`${API_BASE_URL}/categories/${category_id}`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const category = await response.json();
        console.log('Categories:', category); // Handle the categories data as needed
        return category;
    }
    catch (error){
        console.error('Error fetching category:', error);
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
// ===========================
// DOM Manipulation Functions
// ===========================

// Function to switch between sections
function setupSectionSwitching() {
    document.querySelectorAll('.sidebar a').forEach(link => {
        link.addEventListener('click', function() {
            document.querySelectorAll('.content-section').forEach(section => {
                section.classList.add('hidden');
            });
            const targetSection = document.querySelector(this.getAttribute('href'));
            targetSection.classList.remove('hidden');
        });
    });
}

// ===========================
// Dashboard Population Functions
// ===========================

// Fetch data and populate the dashboard
async function populateDashboard() {
    const users = await fetchUsers();
    const products = await fetchProducts();
    const orders = await fetchOrders();
    const categories = await fetchCategories();
    document.getElementById('total-users').innerText = users.length;
    document.getElementById('total-products').innerText = products.length;
    document.getElementById('total-orders').innerText = orders.length;

    populateUsersTable(users);
    populateProductsTable(products);
    populateOrdersTable(orders);
    populateCategoriesTable(categories);
}

function populateUsersTable(users) {
    const tbody = document.getElementById('users-table-body');
    tbody.innerHTML = '';
    users.forEach(user => {
        const row = `<tr>
                <td>${user.id}</td>
                <td>${user.firstname}</td>
                <td>${user.lastname}</td>
                <td>${user.email}</td>
                <td>${user.phone}</td>
                <td>${user.address}</td>
                <td>
                    <button onclick="openEditUserModal(${user.id})">Edit</button>
                    <button onclick="deleteUser (${user.id})">Delete</button>
                </td>
            </tr>`;
        tbody.innerHTML += row;
    });
}

function populateProductsTable(products) {
    const tbody = document.getElementById('products-table-body');
    tbody.innerHTML = '';
    products.forEach(product => {
        const row = `<tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>$${product.price.toFixed(2)}</td>
                <td>${product.stockQuantity}</td>
                <td>
                    <button onclick="editProductModal(${product.id})">Edit</button>
                    <button onclick="deleteProduct(${product.id})">Delete</button>
                </td>
            </tr>`;
        tbody.innerHTML += row;
    });
}
function populateCategoriesTable(categories){
    const tbody = document.getElementById('categories-table-body');
    tbody.innerHTML = '';
    categories.forEach(category => {
        const row = `<tr>
                <td>${category.categoryId}</td>
                <td>${category.name}</td>
               
                <td>${category.description}</td>
                <td>
                    <button onclick="editCategoryModal(${category.id})">Edit</button>
                    <button onclick="deleteCategory(${category.id})">Delete</button>
                </td>
            </tr>`;
        tbody.innerHTML += row;
    });
}
function populateOrdersTable(orders) {
    const tbody = document.getElementById('orders-table-body');
    tbody.innerHTML = '';
    orders.forEach(order => {
        const row = `<tr>
                <td>${order.id}</td>
                <td>${order.user.name}</td>
                <td>$${order.total.toFixed(2)}</td>
                <td>${order.status}</td>
            </tr>`;
        tbody.innerHTML += row;
    });

}
document.addEventListener('DOMContentLoaded', async () => {
    const categorySelect = document.getElementById('addCategory');

    // Function to populate the <select> element with fetched categories
    function populateCategorySelect(categories) {
        categories.forEach(category => {
            console.log(category.name);
            const option = document.createElement('option');
            option.value = category.id; // Set the value attribute
            option.textContent = category.name; // Set the display text
            option.placeholder = 'Select a category';
            categorySelect.appendChild(option);
        });
    }

    // Fetch categories and populate the select element
    try {
        const categories = await fetchCategories(); // Wait for the categories to be fetched
        if (categories) {
            populateCategorySelect(categories); // Pass the categories array to the function
        } else {
            console.error("Categories data is null or undefined.");
        }
    } catch (error) {
        console.error("Error populating categories:", error);
    }
})

// ===========================
// Product Management Functions
// ===========================
function closeProductModal() {
    document.getElementById('addProductModal').style.display = 'none';
}
function openProductModal() {
    document.getElementById('addProductModal').style.display = 'block'
}
async function addProduct(event){
    event.preventDefault();
    const product = {
        productName:document.getElementById('addProductName').value,
        price:document.getElementById('addProductPrice').value,
        stockQuantity:document.getElementById('addProductStockQuantity').value,
        description:document.getElementById('addDescription').value,
        category:document.getElementById('addCategory').value
    };
    try{
        const response = await fetch(`${API_BASE_URL}/products`,{
           method: 'POST',
           headers:{
               'Content-Type':'application/json'
           },
           body: JSON.stringify(product)
        });
        if (response.ok){
            alert('Product added successfully');
            closeProductModal();
            await populateProductsTable();
        }
        else{
            alert('Something went wrong, product could not be inserted');
        }
    }
    catch (error){
        console.error(error);

    }

}
function closeEditProductModal(){document.getElementById('editProductModal').style.display='none';}
function editProductModal(product_id) {
    document.getElementById('editProductModal').style.display = 'block';

    const product = fetchProductById(product_id);
    document.getElementById('editProductName').value = product.name;
    document.getElementById('editPrice').value = product.price;
    document.getElementById('editStockQuantity').value = product.stockQuantity,
        document.getElementById('editDescription').value = product.description,
        document.getElementById('editProductCategory').value = product.category
}
async function editProduct(event){
    event.preventDefault();
    const productId =  document.getElementById('editProductId').value;
    const product={
        productId:productId,
        name:document.getElementById('editProductName').value,
        price:document.getElementById('editProductPrice').value,
        stockQuantity:document.getElementById('editProductStockQuantity').value,
        description:document.getElementById('editDescription').value,
        category:document.getElementById('editProductCategory').value
    }
    try{
        const response = await fetch(`${API_BASE_URL}/products`,{
            method: 'POST',
            headers: {'Content-Type':'application/json'},
            JSON:JSON.stringify(product)
        });
        if (response.ok) {
            alert("Product updated successfully!");
            closeEditProductModal(); // Close the edit modal
            await populateProductsTable(); // Refresh the user list
        } else {
            alert("Failed to update product. Please try again.");
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while updating the product.");
    }
}
async function deleteProduct(product_id){
    try {
        const response = await fetch(`${API_BASE_URL}/users/${product_id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(product_id)
        });

        if (response.ok) {
            alert("Product deleted successfully!");
            closeEditProductModal(); // Close the modal
            await populateDashboard();
        } else {
            alert("Failed to delete Product. Please try again.");
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while deleting the Product.");
    }
}

// ===========================
// Category Management Functions
// ===========================
function openAddCategoryModal() {
    document.getElementById('addCategoryModal').style.display = 'block';
}
function closeAddCategoryModal(){
    document.getElementById('addCategoryModal').style.display = 'none';
}
async function addCategory(event){
    event.preventDefault();
    const category = {
        name:document.getElementById('addCategoryName'),
        description:document.getElementById('addCategoryDescription')
    };
    try{
        const response = await fetch(`${API_BASE_URL}/categories`,{
           method : 'POST',
           headers:{'Content-Type': 'application/json'},
           JSON: JSON.stringify(category)

        });
        if (response.ok){
            alert('Category added successfully');
            closeAddCategoryModal();
        }
        else{
            alert('Category could not be added successfully');
        }
    }
    catch (e){
        console.error('Error',e);
    }
}

function closeEditCategoryModal(){
    document.getElementById('editCategoryModal').style.display='none';
}
function editCategoryModal(category_id){
    document.getElementById('editCategoryModal').style.display = 'block';

    const category = fetchCategoryById(category_id);
    document.getElementById('editProductId').value = category_id;
    document.getElementById('editCategoryName').value = category.name;
    document.getElementById('editCategoryDescription').value = category.description;


}
async function editCategory(event){
    event.preventDefault();
    const category_id = document.getElementById('editCategoryId').value;
    const category = {
        id: category_id,
        name:document.getElementById('editCategoryName'),
        description:document.getElementById('editCategoryDescription')
    };
    try{
        const response = await fetch(`${API_BASE_URL}/categories/${category_id}`,{
            method : 'PUT',
            headers:{'Content-Type': 'application/json'},
            JSON: JSON.stringify(category)

        });
        if (response.ok){
            alert('Category updated successfully');
            closeAddCategoryModal();
        }
        else{
            alert('Category could not be updated successfully');
        }
    }
    catch (e){
        console.error('Error',e);
    }
}

async function deleteCategory(category_id){
    try {
        const response = await fetch(`${API_BASE_URL}/categories/${category_id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(category_id)
        });

        if (response.ok) {
            alert("Category deleted successfully!");
            closeEditCategoryModal(); // Close the modal

        } else {
            alert("Failed to delete Category. Please try again.");
            await populateDashboard();
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while deleting the Category.");
    }
}
// ===========================
// User Management Functions
// ===========================


async function deleteUser (user_id) {
    try {
        const response = await fetch(`${API_BASE_URL}/users/${user_id}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user_id)
        });

        if (response.ok) {
            alert("User deleted successfully!");
            closeModal(); // Close the modal
            await populateDashboard();
        } else {
            alert("Failed to delete user. Please try again.");
            closeProductModal();
            await populateDashboard();
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while deleting the user.");
    }
}

function validatePasswords() {
    const password = document.getElementById("password").value;
    const confirmPassword = document.getElementById("confirmPassword").value;
    if (password !== confirmPassword) {
        alert("Passwords do not match!");
        return false;
    }
    return true;
}

function addUser () {
    // Show the modal
    document.getElementById("userModal").style.display = "block";
}

function closeModal() {
    // Hide the modal
    document.getElementById("userModal").style.display = "none";
}

window.onclick = function(event) {
    const modal = document.getElementById("userModal");
    if (event.target === modal) {
        closeModal();
    }
}

async function submitUserForm(event) {
    event.preventDefault(); // Prevent the default form submission

    if (!validatePasswords()) {
        return; // Stop if passwords do not match
    }

    // Gather form data
    const user = {
        firstname: document.getElementById("firstname").value,
        lastname: document.getElementById("lastname").value,
        email: document.getElementById("email").value,
        password: document.getElementById("password").value,
        phone: document.getElementById("phone").value,
        address: document.getElementById("address").value,
        role: document.getElementById("role").value
    };

    try {
        const response = await fetch(`${API_BASE_URL}/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        });

        if (response.ok) {
            alert("User registered successfully!");
            closeModal(); // Close the modal
            await populateDashboard(); // Refresh the user list
        } else {
            alert("Failed to register user. Please try again.");
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while registering the user.");
    }
}

function fetchUserById(id) {
    // Replace this with your actual API call or data retrieval logic.
    const users = fetchUsers();

    return users.find(user => user.id === id);
}

function openEditUserModal(userId) {
    document.getElementById("editUserModal").style.display = "block";
    // Fetch user data and populate the edit form
    const user = fetchUserById(userId);
    document.getElementById("editUserId").value = user.id;
    document.getElementById("editFirstname").value = user.firstname;
    document.getElementById("editLastname").value = user.lastname;
    document.getElementById("editEmail").value = user.email;
    document.getElementById("editPhone").value = user.phone;
    document.getElementById("editAddress").value = user.address;
    document.getElementById("editRole").value = user.role;
}

function closeEditUserModal() {
    document.getElementById("editUserModal").style.display = "none"; // Hide the edit modal
}

async function submitEditUser (event) {
    event.preventDefault(); // Prevent the default form submission

    const userId = document.getElementById("editUserId").value;
    const updatedUser  = {
        id: userId,
        firstname: document.getElementById("editFirstname").value,
        lastname: document.getElementById("editLastname").value,
        email: document.getElementById("editEmail").value,
        phone: document.getElementById("editPhone").value,
        address: document.getElementById("editAddress").value,
        role: document.getElementById("editRole").value
    };

    try {
        const response = await fetch(`${API_BASE_URL}/users/${userId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedUser)
        });

        if (response.ok) {
            alert("User updated successfully!");
            closeEditUserModal(); // Close the edit modal
            await populateDashboard(); // Refresh the user list
        } else {
            alert("Failed to update user. Please try again.");
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while updating the user.");
    }
}

// ===========================
// Initialization
// ===========================

window.onload = function() {
    setupSectionSwitching(); // Set up section switching
    populateDashboard(); // Call the function to populate the dashboard on page load
};
// Call the function to fetch all data
fetchAllData();