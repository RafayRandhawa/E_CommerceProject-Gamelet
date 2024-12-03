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
async function fetchShippings() {
    try {
        const response = await fetch(`${API_BASE_URL}/shippings`);
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        const shippings = await response.json();
        console.log('Shippings:', shippings); // Handle the shipping data as needed
        return shippings;
    } catch (error) {
        console.error('Error fetching shippings:', error);
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
async function fetchSessionUser() {
    try {
        const response = await fetch('/api/session/data');
        if (!response.ok) {
            throw new Error('Failed to fetch session data');
        }

        const sessionData = await response.json();
        console.log('Session Data:', sessionData);

        // Access user data from the response
        const user = sessionData.user;
        console.log('User:', user);

        // Use the user object as needed
        document.getElementById('user-name').textContent = `Welcome, ${user.firstname} ${user.lastname}`;
    } catch (error) {
        console.error('Error fetching session data:', error);
    }
}

// Call the function to load session data
fetchSessionUser();

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
    const reviews = await fetchReviews();
    const shipping = await fetchShippings();
    const payments = await fetchPayments();
    document.getElementById('total-users').innerText = users.length;
    document.getElementById('total-products').innerText = products.length;
    document.getElementById('total-orders').innerText = orders.length;

    populateUsersTable(users);
    populateProductsTable(products);
    populateOrdersTable(orders);
    populateCategoriesTable(categories);
    populateShippingTable(shipping);
    populateReviewsTable(reviews);
    populatePaymentsTable(payments);
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
                <td>${product.productId}</td>
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
                    <button onclick="editCategoryModal(${category.categoryId})">Edit</button>
                    <button onclick="deleteCategory(${category.categoryId})">Delete</button>
                </td>
            </tr>`;
        tbody.innerHTML += row;
    });
}
function populateOrdersTable(orders) {
    const tbody = document.getElementById('orders-table-body');
    tbody.innerHTML = '';
    orders.forEach(order => {
        const rawDateTime = order.createdAt;

        // Split the date and time
        const [date, time] = rawDateTime.split("T");
        const formattedTime = time.split(".")[0]; // Remove nanoseconds, get "23:05:54"

        console.log("Date:", date); // "2024-12-03"
        console.log("Time:", formattedTime); // "23:05:54"
        const row = `<tr>
                <td>${order.orderId}</td>
                <td>${date}</td>
                <td>${order.status}</td>
                <td>${order.user.id}</td>
                <td>${order.shipping.shippingId}</td>
                <td>${order.payment.paymentId}</td>
                <td>${formattedTime}</td>
                <td>
                    <button onclick="cancelOrder(${order.id})">Canecl Order</button>
                    <button onclick="viewOrder(${order.id})">View Order</button>
                </td>
            </tr>`;
        tbody.innerHTML += row;
    });

}
function populateReviewsTable(reviews) {
    const tbody = document.getElementById('reviews-table-body');
    tbody.innerHTML = '';
    reviews.forEach(review => {
        const dateElement = review.reviewDate;
        const rawDate = dateElement.innerText; // e.g., "2024-12-03T23:13:16.268"
        const formattedDate = new Date(rawDate).toISOString().split('T')[0]; // "YYYY-MM-DD"
        const row = `<tr>
                <td>${review.reviewId}</td>
                <td>${review.product.productId}</td>
                <td>${review.user.id}</td>
                <td>${review.rating}</td>
                <td>${review.reviewText}</td>
                <td>${formattedDate}</td>
                <td>${review.isApproved}</td>
              
                <td>
                    <button onclick="approve(${review.reviewId})">Approve</button>
                    <button onclick="rejectReview(${review.reviewId})">Delete</button>
                </td>
            </tr>`;
        tbody.innerHTML += row;
    });

}
function populateShippingTable(shippings) {
    const tbody = document.getElementById('shipping-table-body');
    tbody.innerHTML = '';
    shippings.forEach(shipping => {
        const rawDateTime = shipping.shippingDate;

        // Split the date and time
        const [date, time] = rawDateTime.split("T");
        const formattedTime = time.split(".")[0]; // Remove nanoseconds, get "23:05:54"
        const row = `<tr>
                <td>${shipping.shippingId}</td>
                <td>${shipping.address}</td>
                <td>${shipping.city}</td>
                <td>${shipping.state}</td>
                <td>${shipping.country}</td>
                <td>${shipping.postalCode}</td>
                <td>${date}</td>
                
            </tr>`;
        tbody.innerHTML += row;
    });
}
function populatePaymentsTable(payments) {
    const tbody = document.getElementById('payments-table-body');
    tbody.innerHTML = '';
    payments.forEach(payment => {
        const rawDateTime = payment.paymentDate;

        // Split the date and time
        const [date, time] = rawDateTime.split("T");
        const formattedTime = time.split(".")[0]; // Remove nanoseconds, get "23:05:54"
        const row = `<tr>
                <td>${payment.paymentId}</td>
                <td>${payment.paymentType}</td>
              
                <td>${date}</td>
                <td>${payment.amount}</td>
   
            </tr>`;
        tbody.innerHTML += row;
    });
}

document.addEventListener('DOMContentLoaded', async () => {
    const categorySelect = document.getElementById('addCategory');

    // Function to populate the <select> element with fetched categories
    function populateCategorySelect(categories) {
        categories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.categoryId; // Set the value attribute
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
// Review Management Functions
// ===========================
async function approve(review_id) {

    const apiUrl = `${API_BASE_URL}/reviews/${review_id}`;

    try {
        const response = await fetch(apiUrl, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({review_id}),
        });

        if (response.ok) {
            alert('Review updated successfully');
        } else {
            const errorMessage = await response.text();
            console.error('Error updating review:', errorMessage);
            alert('Failed to update review. Please try again.');
        }
    } catch (error) {
        console.error('Network or server error:', error);
        alert('An error occurred while updating the review.');
    }
}
async function rejectReview(review_id) {
    try {
        const response = await fetch(`${API_BASE_URL}/reviews/${review_id}`, {
            method: 'DELETE',
            headers: 'Content-type:application/json',
            body: JSON.stringify(review_id)
        });
        if (response.ok) {
            alert("Review deleted successfully!");
        } else {
            alert("Failed to delete Review. Please try again.");
        }
    } catch (error) {
        console.error('Error:', error);
        alert("An error occurred while deleting the Review.");
    }
}
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
    const categoryIdValue = document.getElementById('addCategory').value;
    if (!categoryIdValue || isNaN(parseInt(categoryIdValue))) {
        alert('Please select a valid category');
        return;
    }
    alert(document.getElementById('addProductName').value);
    const product = {
        name:document.getElementById('addProductName').value,
        price:document.getElementById('addPrice').value,
        stockQuantity:document.getElementById('addStockQuantity').value,
        description:document.getElementById('addDescription').value,
        category:{
            categoryId: parseInt(document.getElementById('addCategory').value)
        }
    };
    console.log(product);
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
            window.location.href = window.location.origin + window.location.pathname + "#products";
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
        name:document.getElementById('addCategoryName').value,
        description:document.getElementById('addCategoryDescription').value
    };
    console.log(category);
    try{
        const response = await fetch(`${API_BASE_URL}/categories`,{
           method : 'POST',
           headers:{'Content-Type': 'application/json'},
           body: JSON.stringify(category)

        });
        if (response.ok){
            alert('Category added successfully');
            closeAddCategoryModal();
            window.location.reload();
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
async function editCategoryModal(category_id){
    document.getElementById('editCategoryModal').style.display = 'block';

    const category = await fetchCategoryById(category_id);
    document.getElementById('editCategoryId').value = category_id;
    document.getElementById('editCategoryName').value = category.name;
    document.getElementById('editCategoryDescription').value = category.description;


}
async function editCategory(event){
    event.preventDefault();
    const category_id = document.getElementById('editCategoryId').value;
    const category = {
        categoryId: category_id,
        name:document.getElementById('editCategoryName').value,
        description:document.getElementById('editCategoryDescription').value
    };
    try{
        const response = await fetch(`${API_BASE_URL}/categories/${category_id}`,{
            method : 'PUT',
            headers:{'Content-Type': 'application/json'},
            body: JSON.stringify(category)

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
    console.log(user);
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
window.onclick = function(event) {
    const modal = document.getElementById("userModal");
    if (event.target === modal) {
        closeModal();
    }
}
// ===========================
// Order Management Functions
// ===========================

async function cancelOrder(order_id) {

    try {
        const response = await fetch(`${API_BASE_URL}/orders/${order_id}/cancel`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        if (response.ok) {
            alert(`Order ${order_id} has been successfully cancelled.`);
            // Optionally, refresh the order list or update the UI here
        } else if (response.status === 404) {
            alert(`Order ${order_id} not found.`);
        } else {
            alert('Failed to cancel the order. Please try again later.');
        }
    } catch (error) {
        console.error('Error cancelling the order:', error);
        alert('An error occurred while cancelling the order. Please check the console for details.');
    }
}
async function viewOrder(orderId){
    const popup = document.getElementById('products-popup');
    const productsTableBody = document.getElementById('order-products-table-body');

    // Clear existing product rows
    productsTableBody.innerHTML = '';

    // Fetch products associated with the order
    fetch(`${API_BASE_URL}/orders/${orderId}/products`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch products');
            }
            return response.json();
        })
        .then(orderItems => {
            orderItems.forEach(orderItem => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${orderItem.product.productId}</td>
                    <td>${orderItem.product.name}</td>
                    <td>${orderItem.product.description || 'No description'}</td>
                    <td>${orderItem.product.price}</td>
                    <td>${orderItem.product.stockQuantity}</td>
                    <td>${orderItem.product.category.name}</td>
                    <td>${orderItem.product.createdAt}</td>
                    <td>${orderItem.product.updatedAt}</td>
                `;
                productsTableBody.appendChild(row);
            });
        })
        .catch(error => {
            console.error('Error fetching products:', error);
            alert('Failed to load products');
        });

    // Show the popup
    popup.classList.remove('hidden');
}

function closeProductsPopup() {
    const popup = document.getElementById('products-popup');
    popup.classList.add('hidden');
}
// ===========================
// Initialization
// ===========================

window.onload = function() {
    setupSectionSwitching(); // Set up section switching
    populateDashboard(); // Call the function to populate the dashboard on page load
};
async function logout(){
    try{
        await fetch(`${API_BASE_URL}/logout`);
    } catch (error) {
        console.error('Error:', error);
    }
    window.location.href="/login";
}
// Call the function to fetch all data
fetchAllData();