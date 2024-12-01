function showEditField(fieldId) {
    const field = document.getElementById(`${fieldId}-edit`);
    field.style.display = field.style.display === "none" ? "block" : "none";
}

function changeFirstName() {
    const newFirstName = document.getElementById("first-name-input").value;
    // Add AJAX call to update the server
    alert(`First name changed to: ${newFirstName}`);
}

function changeLastName() {
    const newLastName = document.getElementById("last-name-input").value;
    // Add AJAX call to update the server
    alert(`Last name changed to: ${newLastName}`);
}

function changePhone() {
    const newPhone = document.getElementById("phone-input").value;
    // Add AJAX call to update the server
    alert(`Phone changed to: ${newPhone}`);
}

function changeAddress() {
    const newAddress = document.getElementById("address-input").value;
    // Add AJAX call to update the server
    alert(`Address changed to: ${newAddress}`);
}

function changePassword() {
    // Add logic to redirect or open password change modal
    alert("Redirecting to change password...");
}
