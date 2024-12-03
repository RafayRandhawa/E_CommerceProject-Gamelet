async function addToCart(event) {
    event.preventDefault(); // Prevent the default form submission

    // Get the form data
    const form = event.target;
    const productId = form.productId.value;
    const quantity = form.quantity.value;

    // Prepare the data to be sent
    const data = new URLSearchParams();
    data.append('productId', productId);
    data.append('quantity', quantity);

    try {
        // Send the POST request
        const response = await fetch('/cart/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: data.toString()
        });

        if (response.ok) {
            const result = await response.text();
            console.log('Item added to cart successfully:', result);
            alert('Item added to cart!');
            await updateCartCount();
        } else {
            console.error('Error adding item to cart:', response.statusText);
            alert('Failed to add item to cart.');
        }
    } catch (error) {
        console.error('Error adding item to cart:', error);
        alert('An error occurred. Please try again.');
    }

}

// Checkout button event listener
document.getElementById('checkout-btn').addEventListener('click', checkout);

// Checkout function
// Checkout function
async function checkout() {
    try {
        // Step 1: Fetch cart items
        const response = await fetch('/cart/items'); // Endpoint to fetch cart items
        if (!response.ok) {
            throw new Error('Failed to load cart items for checkout');
        }
        const cartItems = await response.json();

        // Step 2: Check if cart is empty
        if (cartItems.length === 0) {
            alert("Your cart is empty. Please add items to the cart before proceeding.");
            return;
        }
        window.location.href = '/checkout/page';  // Change this URL to your actual confirm checkout page URL

    } catch (error) {
        console.error("Checkout error:", error);
        alert('An error occurred during checkout. Please try again.');
    }
}

