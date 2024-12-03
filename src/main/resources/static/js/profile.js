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

// Feedback Modal Logic
const feedbackModal = document.getElementById('feedback-modal');
const closeFeedbackModal = document.querySelector('.close-feedback-modal');
const feedbackBtn = document.getElementById('feedback-btn');
let currentOrderId; // To store the selected order ID for feedback

// Open Feedback Modal
feedbackBtn.addEventListener('click', () => {
    feedbackModal.style.display = 'flex';
    currentOrderId = document.getElementById('order-id').innerText; // Get the current order ID
});

// Close Feedback Modal
closeFeedbackModal.addEventListener('click', () => {
    feedbackModal.style.display = 'none';
});

feedbackModal.addEventListener('click', (event) => {
    if (event.target === feedbackModal) {
        feedbackModal.style.display = 'none';
    }
});

// Handle Feedback Form Submission
document.getElementById('feedback-form').addEventListener('submit', async (event) => {
    event.preventDefault(); // Prevent default form submission

    const feedbackData = {
        rating: document.getElementById('rating').value,
        reviewText: document.getElementById('review-text').value,
        orderId: currentOrderId // Use the order ID for feedback
    };

    try {
        const response = await fetch('/profile/submit-feedback', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(feedbackData)
        });

        if (response.ok) {
            alert('Feedback submitted successfully!');
            feedbackModal.style.display = 'none';
            document.getElementById('feedback-form').reset(); // Clear the form
        } else {
            alert('Failed to submit feedback. Please try again.');
        }
    } catch (error) {
        console.error('Error submitting feedback:', error);
    }
});
