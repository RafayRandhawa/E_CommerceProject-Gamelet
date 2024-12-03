
const API_BASE_URL = 'http://localhost:8085/api/admin';
async function populateCategoriesDropdown() {
    const categoriesMenu = document.getElementById('categories-menu');
    categoriesMenu.innerHTML = ''; // Clear existing items
    const categories = await fetchCategories();

    if (categories && Array.isArray(categories)) {
        categories.forEach(category => {
            console.log(category)
            const categoryItem = document.createElement('li');
            categoryItem.innerHTML = `
                <a href="/home/products?categoryId=${category.categoryId}">
                    ${category.name}
                </a>`;
            categoriesMenu.appendChild(categoryItem);
        });
    } else {
        console.error('Failed to load categories');
    }
}

// Function to fetch categories from the backend
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

// Call the function when the page loads
document.addEventListener('DOMContentLoaded', populateCategoriesDropdown);

function logout() {
    // Make a request to the server to invalidate the session
    fetch('/home/logout', { method: 'POST' })
        .then(response => {
            if (response.ok) {
                // Refresh the homepage
                window.location.reload();
            } else {
                console.error('Logout failed');
            }
        })
        .catch(error => console.error('Error during logout:', error));
}

function buyProduct(productId, productName, productPrice, selectedQuantity) {
    const product = {
        id: productId,
        name: productName,
        price: productPrice,
        quantity: selectedQuantity
    };

    addToCart(product); // Add the product to the cart
}

const cartBtn = document.getElementById("cart-btn");
document.getElementById("cart-btn").addEventListener("click", toggleCartPopup);

// cartBtn.addEventListener("click", async () => {
//     console.log("Cart button clicked");
//
//     // Toggle the cart popup visibility
//     cartPopup.classList.toggle("hidden");
//
//     // If the popup is now visible, load the cart items
//     if (!cartPopup.classList.contains("hidden")) {
//         await loadCartItems();
//     }
// });

async function toggleCartPopup() {
    console.log("Button Clicked")
    const popup = document.getElementById("cart-popup")
    if(popup.style.display==='none'|| popup.style.display === ''){
        popup.style.display='block';
        await loadCartItems();
    }
    else{
        popup.style.display='none';
    }
}

// Fetch cart items from the server and populate the popup
async function loadCartItems() {
    try {
        const response = await fetch("/cart/items");
        if (response.ok) {
            const cartItems = await response.json();
            console.log("Cart items response:", cartItems); // Log API response

            const cartItemsContainer = document.getElementById("cart-items");
            if (!cartItemsContainer) {
                console.error("Cart items container not found!");
                return;
            }

            cartItemsContainer.innerHTML = ""; // Clear previous items

            cartItems.forEach((item) => {
                const itemDiv = document.createElement("div");
                itemDiv.classList.add(`cart-item-${item.cartItemId}`);
                itemDiv.innerHTML = `
                    <div class="cart-item-details">
                        <h4>${item.product.name}</h4>
                        <p>Quentity: ${item.quantity}</p>
                        <p>Price: ${item.totalPrice}</p>
                    
                    </div>
                    <button onclick="deleteCartItem(${item.cartItemId})" class="delete-btn">Delete</button>
                    <hr>
                `;
                console.log("Adding item to DOM:", itemDiv); // Log each item added
                cartItemsContainer.appendChild(itemDiv);
                // Create a line (horizontal rule) after each cart item

            });

        } else {
            console.error("Failed to load cart items, status:", response.status);
        }
    } catch (error) {
        console.error("Error fetching cart items:", error);
    }
}


// Call this when toggling the cart popup to ensure updated data
//document.getElementById("cart-btn").addEventListener("click", loadCartItems);
// document.addEventListener("DOMContentLoaded", () => {
//     const cartBtn = document.getElementById("cart-btn");
//
//     if (cartBtn) {
//         cartBtn.addEventListener("click", () => {
//             //toggleCartPopup();
//             loadCartItems() // Load items when cart is toggled
//         });
//     } else {
//         console.error("Cart button not found in the DOM.");
//     }
// });

async function deleteCartItem(itemId) {
    try {
        const response = await fetch(`/cart/delete/${itemId}`, {
            method: 'DELETE',

        });

        if (response.ok) {
            // Remove the item from the UI after successful deletion
            const itemDiv = document.getElementById(`cart-item-${itemId}`);
            if (itemDiv) {
                itemDiv.remove()
                await loadCartItems()
            }
            console.log(`Item ${itemId} deleted successfully.`);
        } else {
            console.error(`Failed to delete item ${itemId}, status: ${response.status}`);
        }
    } catch (error) {
        console.error("Error deleting item:", error);
    }
}
async function updateCartCount() {
    try {
        // Fetch the cart items from the backend
        const response = await fetch(`/cart/items`);

        // Check if the request was successful
        if (response.ok) {
            // Get the cart items from the response
            const cartItems = await response.json();

            // Update the cart count display based on the number of items in the cart
            document.getElementById('cart-count').textContent = cartItems.length;
        } else {
            console.error("Failed to fetch cart items");
        }
    } catch (error) {
        console.error("Error fetching cart items:", error);
    }
}
document.getElementById('close-cart-btn').addEventListener('click', () => {
    const popup = document.getElementById('cart-popup');
    popup.style.display = 'none'; // Hide the cart popup
});

updateCartCount();

// Example: Pause/resume the animation when hovering
document.querySelector('.products-section').addEventListener('mouseover', function() {
    document.querySelector('.carousel-track').style.animationPlayState = 'paused';
});

document.querySelector('.products-section').addEventListener('mouseout', function() {
    document.querySelector('.carousel-track').style.animationPlayState = 'running';
});