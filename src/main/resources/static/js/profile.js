// Edit Profile Modal Logic
const editProfileBtn = document.getElementById('edit-profile-btn');
const profileModal = document.getElementById('profile-modal');

editProfileBtn.addEventListener('click', () => {
    profileModal.style.display = 'flex';
});

profileModal.addEventListener('click', (event) => {
    if (event.target === profileModal) {
        profileModal.style.display = 'none';
    }
});

document.getElementById('edit-profile-form').addEventListener('submit', (e) => {
    profileModal.style.display = 'none';
});

// View Order Modal and Button Logic
const modal = document.getElementById('order-details-modal');
const closeModal = document.querySelector('.close-modal');

document.addEventListener('click', async (event) => {
    // Check if the clicked element is the "View Order" button
    if (event.target.classList.contains('view-order-btn')) {
        const orderId = event.target.getAttribute('data-order-id'); // Use the corrected attribute name

        // Fetch order details from the server
        try {
            const response = await fetch(`/profile/order/details/${orderId}`);
            if (response.ok) {
                const order = await response.json();
                console.log(order);

                // Populate modal with order details
                document.getElementById('order-id').innerText = order.orderId;
                const orderDate = new Date(order.orderDate);  // Convert to Date object
                const formattedDate = orderDate.toLocaleString();  // Get local date-time string
                document.getElementById('order-date').innerText = formattedDate;
                document.getElementById('order-status').innerText = order.status;
                document.getElementById('order-user').innerText = `${order.user.firstname} ${order.user.lastname}`;
                document.getElementById('payment-method').innerText = order.payment.paymentType;

                // Show modal
                const modal = document.getElementById('order-details-modal'); // Replace with your modal's ID
                modal.style.display = 'flex';
            } else {
                console.error("Failed to fetch order details");
            }
        } catch (error) {
            console.error("Error fetching order details:", error);
        }
    }
});


// Close modal
closeModal.addEventListener('click', () => {
    modal.style.display = 'none';
});

modal.addEventListener('click', (event) => {
    if (event.target === modal) {
        modal.style.display = 'none';
    }
});
