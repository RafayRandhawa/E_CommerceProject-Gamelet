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
        } else {
            console.error('Error adding item to cart:', response.statusText);
            alert('Failed to add item to cart.');
        }
    } catch (error) {
        console.error('Error adding item to cart:', error);
        alert('An error occurred. Please try again.');
    }
}