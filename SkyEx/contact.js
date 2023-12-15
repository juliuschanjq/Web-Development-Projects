function addDiscussion() {
    var username = document.getElementById('username').value;
    var message = document.getElementById('message').value;

    if (username && message) {
        var discussionList = document.getElementById('discussion-list');

        var discussionDiv = document.createElement('div');
        discussionDiv.className = 'discussion';

        var discussionContent = '<strong>' + username + ':</strong> ' + message;
        discussionDiv.innerHTML = discussionContent;

        discussionList.appendChild(discussionDiv);

        // Clear input fields
        document.getElementById('username').value = '';
        document.getElementById('message').value = '';
    } else {
        alert('Please enter your name and message.');
    }
}
