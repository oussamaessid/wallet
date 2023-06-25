package com.example.hotelwallet.presentation.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.hotelwallet.R
import com.example.hotelwallet.databinding.FragmentChatBinding
import com.example.hotelwallet.domain.model.ChatMessage
import com.example.hotelwallet.domain.model.SubMenu
import com.example.hotelwallet.domain.model.ToolbarConfiguration
import com.example.hotelwallet.presentation.favorite.FavoriteAdapter
import com.example.hotelwallet.presentation.misc.BaseFragment
import com.example.hotelwallet.utility.KEY_PRODUCT_DELETE_FAVORITE
import com.example.hotelwallet.utility.KEY_PRODUCT_DETAILS

/**
 * A simple [Fragment] subclass.
 * Use the [ChatFragment] factory method to
 * create an instance of this fragment.
 */
class ChatFragment : BaseFragment<FragmentChatBinding>(
    FragmentChatBinding::inflate,
    toolbarConfiguration = ToolbarConfiguration(
        visibility = View.VISIBLE,
        btnBackVisibility = View.VISIBLE,
        title = R.string.txt_title_chat
    )
) {

    private lateinit var chatAdapter: ChatMessageAdapter
    private var chatList = mutableListOf<ChatMessage>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatList.add(ChatMessage(desc = "success", ques = "Hello Question", res = "Hello response", time = "2023-06-16 21:21"))
        chatAdapter = ChatMessageAdapter(chatList)

        binding.recyclerViewChat.setHasFixedSize(true)
        binding.recyclerViewChat.isNestedScrollingEnabled = false
        binding.recyclerViewChat.adapter = chatAdapter

        setBottomNavigation(false)
    }
}