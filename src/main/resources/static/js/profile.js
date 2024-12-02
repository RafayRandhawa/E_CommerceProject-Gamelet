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
    e.preventDefault();

    alert("Profile updated successfully!");
    profileModal.style.display = 'none';
});
