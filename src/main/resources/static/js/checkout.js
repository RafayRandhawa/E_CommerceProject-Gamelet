
async function submitCheckout() {
    // Get the values from the form
    const paymentType = document.getElementById("paymentType").value;
    const amount = async () => {
        const response = await fetch(`/cart/cart`);
        if (response.ok) {
            // Wait for the response body to be parsed as JSON
            const data = await response.json();
            //return data.payment.amount;  // Assuming the response structure has 'payment' with 'amount'
            let amount = 0;
            data.cartItems.forEach(item => {
                    amount += item.totalPrice;
                }
            )
            return amount;
        } else {
            alert("Checkout failed");
            return null;  // Return null or any value that makes sense in case of failure
        }
    }

    const checkoutAmount = await amount();
    console.log("Amount " + checkoutAmount);
    const address = document.getElementById("address").value;
    const city = document.getElementById("city").value;
    const state = document.getElementById("state").value;
    const country = document.getElementById("country").value;
    const postalCode = document.getElementById("postalCode").value;
    if (!paymentType || !address || !city || !state || !country || !postalCode) {
        alert("Please fill in all the fields before proceeding.");
        return;
    }
    // Assume you have the current user's ID stored in the session
    const userId = sessionStorage.getItem("userId"); // Example, replace with actual method to get user info

    // Create the payment and shipping objects
    const payment = {
        paymentType: paymentType,
        amount: checkoutAmount
    };

    const shipping = {
        address: address,
        city: city,
        state: state,
        country: country,
        postalCode: postalCode
    };

    // Create the order object, including the user and payment & shipping data
    const order = {
        user: {id: userId},
        payment: payment,
        shipping: shipping
    };

    // Send the POST request to the backend
    await fetch('http://localhost:8085/checkout/process', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(order)
    })
        .then(response => response.json())
        .then(async data => {
            console.log("Order placed successfully:", data);
            alert("Order placed successfully!");
            await fetch('http://localhost:8085/cart/clear', {method: 'DELETE'});
            window.location.href = "/home/";  // Redirect to confirmation page
        })
        .catch(error => {
            console.error("Error placing order:", error);
            alert("An error occurred. Please try again.");
        });
}

