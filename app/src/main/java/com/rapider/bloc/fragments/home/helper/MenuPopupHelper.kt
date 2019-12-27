package com.rapider.bloc.fragments.home.helper

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rapider.R
import com.rapider.bloc.fragments.home.helper.MenuItemAdapter.MenuItemAdapter.LARGE_ITEM
import com.rapider.bloc.fragments.home.helper.MenuItemAdapter.MenuItemAdapter.NORMAL_ITEM
import com.rapider.extensions.*
import com.rapider.models.ToggleMenuItem
import com.rapider.utils.fixStatusBarStateForPopupShow
import com.rapider.views.RoundRectOutlineProvider
import razerdp.basepopup.BasePopupWindow
import razerdp.basepopup.QuickPopupBuilder
import razerdp.basepopup.QuickPopupConfig

class MenuPopupHelper(var fragment: Fragment, var context:Context) {
    private var popup: BasePopupWindow?= null

    private lateinit var mainMenuView: RecyclerView
    private lateinit var loginIconView: ImageView
    private lateinit var headerText:View
    private lateinit var actionGroupView:View
    private lateinit var toolboxView: RecyclerView
    private lateinit var quickSettingIconView: ImageView
    private lateinit var toolboxHeaderText:View
    private var showMainMenu=true

    fun show() {
        if (popup==null){
            val slideUpIn= AnimationUtils.loadAnimation(context, R.anim.slide_up_in)
            val slideDownOut= AnimationUtils.loadAnimation(context, R.anim.slide_down_out)

            popup= QuickPopupBuilder.with(context)
                    .config(QuickPopupConfig()
                            .gravity(Gravity.BOTTOM)
                            .withShowAnimation(slideUpIn)
                            .withDismissAnimation(slideDownOut))
                    .contentView(R.layout.dialog_home_menu).build().apply {
                        fixStatusBarStateForPopupShow(fragment.requireActivity(),this)
                    }
            popup?.contentView?.findViewById<View>(R.id.main_menu)?.apply {
                //设置圆角
                clipToOutline=true
                outlineProvider= RoundRectOutlineProvider(context.dp2px(18).toFloat())
                val container=this
                container.findViewById<View>(R.id.header_action_group)?.apply {
                    //设置圆角
                    clipToOutline=true
                    outlineProvider= RoundRectOutlineProvider(context.dp2px(10000).toFloat())
                }
                mainMenuView=container.findViewById(R.id.menu_grid)
                initMainMenu(mainMenuView)
                toolboxView=container.findViewById(R.id.toolbox_grid)
                initToolBoxMenu(toolboxView)
                loginIconView=container.findViewById(R.id.login_icon)
                headerText=container.findViewById(R.id.header_text)
                actionGroupView=container.findViewById(R.id.header_action_group)
                quickSettingIconView=container.findViewById(R.id.icon_quick_setting)
                toolboxHeaderText=container.findViewById(R.id.toolbox_header_text)
            }
        }
        popup?.showPopupWindow()
    }

    private fun initMainMenu(recyclerView: RecyclerView) {
        val list = ArrayList<ToggleMenuItem>()
        initMainMenuData(list)
        recyclerView.apply {
            adapter= MenuItemAdapter(list)
            layoutManager= GridLayoutManager(recyclerView.context,4)
        }

    }

    private fun initMainMenuData(list: ArrayList<ToggleMenuItem>) {
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_bookmark_30,
                        disableIconResId = R.drawable.ic_bookmark_disable_30,
                        enableLabelResId = R.string.bookmark_and_history,
                        disableLabelResId = R.string.bookmark_and_history
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_download_30,
                        disableIconResId = R.drawable.ic_download_disable_30,
                        enableLabelResId = R.string.download,
                        disableLabelResId = R.string.download
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_cloud_30,
                        disableIconResId = R.drawable.ic_cloud_disable_30,
                        enableLabelResId = R.string.online_disk,
                        disableLabelResId = R.string.online_disk
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_refresh_30,
                        disableIconResId = R.drawable.ic_refresh_disable_30,
                        enableLabelResId = R.string.refresh,
                        disableLabelResId = R.string.refresh,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_clear_robot_30,
                        disableIconResId = R.drawable.ic_clear_robot_disable_30,
                        enableLabelResId = R.string.private_mode,
                        disableLabelResId = R.string.private_mode
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_add_bookmark_30,
                        disableIconResId = R.drawable.ic_add_bookmark_disable_30,
                        enableLabelResId = R.string.add_bookmark,
                        disableLabelResId = R.string.add_bookmark,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_moon_30,
                        disableIconResId = R.drawable.ic_moon_30,
                        enableLabelResId = R.string.night_mode,
                        disableLabelResId = R.string.night_mode
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_toolbox_30,
                        disableIconResId = R.drawable.ic_toolbox_disable_30,
                        enableLabelResId = R.string.toolbox,
                        disableLabelResId = R.string.toolbox,
                        action = {
                            showMainMenu=false

                            toolboxHeaderText.show()
                            mainMenuView.hide()
                            loginIconView.hide()
                            headerText.hide()
                            actionGroupView.startAnimationWithEndCallBack(R.anim.micro_slide_fade_out){
                                if (showMainMenu){
                                    actionGroupView.show()
                                }else{
                                    actionGroupView.hide()
                                }
                            }
                            toolboxView.startAnimationWithEndCallBack(R.anim.micro_slide_up){
                                if (showMainMenu){
                                    toolboxView.hide()
                                }else{
                                    toolboxView.show()
                                }
                            }
                            quickSettingIconView.startAnimationWithEndCallBack(R.anim.micro_slide_down){
                                if (showMainMenu){
                                    quickSettingIconView.hide()
                                }else{
                                    quickSettingIconView.show()
                                }

                            }
                        }
                )
        )
    }

    private fun initToolBoxMenu(recyclerView: RecyclerView) {
        val list = ArrayList<ToggleMenuItem>()
        initToolboxMenuData(list)
        recyclerView.apply {
            adapter= MenuItemAdapter(list)
            layoutManager= GridLayoutManager(recyclerView.context,4)
        }
    }
    private fun initToolboxMenuData(list: ArrayList<ToggleMenuItem>) {
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_ua_30,
                        disableIconResId = R.drawable.ic_ua_30,
                        enableLabelResId = R.string.ua_settings,
                        disableLabelResId = R.string.ua_settings
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_find_in_page_30,
                        disableIconResId = R.drawable.ic_find_in_page_disable_30,
                        enableLabelResId = R.string.find_in_page,
                        disableLabelResId = R.string.find_in_page,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_report_30,
                        disableIconResId = R.drawable.ic_report_disable_30,
                        enableLabelResId = R.string.report_illegal,
                        disableLabelResId = R.string.report_illegal,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_rotate_30,
                        disableIconResId = R.drawable.ic_rotate_disable_30,
                        enableLabelResId = R.string.landscape,
                        disableLabelResId = R.string.landscape,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_translate_30,
                        disableIconResId = R.drawable.ic_translate_disable_30,
                        enableLabelResId = R.string.translate,
                        disableLabelResId = R.string.translate,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_empty_84,
                        disableIconResId = R.drawable.ic_empty_84,
                        enableLabelResId = R.string.empty,
                        disableLabelResId = R.string.empty,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_empty_84,
                        disableIconResId = R.drawable.ic_empty_84,
                        enableLabelResId = R.string.empty,
                        disableLabelResId = R.string.empty,
                        enable = false
                )
        )
        list.add(
                ToggleMenuItem(
                        enableIconResId = R.drawable.ic_return_40,
                        disableIconResId = R.drawable.ic_return_40,
                        enableLabelResId = R.string.empty,
                        disableLabelResId = R.string.empty,
                        action = {
                            showMainMenu=true
                            toolboxView.hide()
                            quickSettingIconView.hide()
                            toolboxHeaderText.hide()
                            mainMenuView.startAnimationWithEndCallBack(R.anim.micro_slide_down){
                                if (showMainMenu){
                                    mainMenuView.show()
                                }else{
                                    mainMenuView.hide()
                                }

                            }
                            loginIconView.startAnimationWithEndCallBack(R.anim.micro_slide_up){
                                if (showMainMenu){
                                    loginIconView.show()
                                }else{
                                    loginIconView.hide()
                                }
                            }
                            headerText.show()
                            actionGroupView.startAnimationWithEndCallBack(R.anim.micro_slide_fade_in){
                                if (showMainMenu){
                                    actionGroupView.show()
                                }else{
                                    actionGroupView.hide()
                                }
                            }

                        }
                )
        )
    }
}

class MenuItemVH(view: View) : RecyclerView.ViewHolder(view) {
    var iconView: ImageView = view.findViewById(R.id.icon)
    var labelTextView: TextView = view.findViewById(R.id.label)
}
class LargeMenuItemVH(view: View) : RecyclerView.ViewHolder(view) {
    var iconView: ImageView = view.findViewById(R.id.icon)
}
class MenuItemAdapter(var data: List<ToggleMenuItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    object MenuItemAdapter{
        const val NORMAL_ITEM=1
        const val LARGE_ITEM=2
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType== LARGE_ITEM){
            val view =
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_menu_item_action_large, parent, false)
            LargeMenuItemVH(view)
        }else{
            val view =
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_menu_item_action, parent, false)
            MenuItemVH(view)
        }

    }
    override fun getItemViewType(position: Int): Int {
        val item=data[position]
        if (item.enableIconResId==R.drawable.ic_return_40){
            return LARGE_ITEM
        }
        return NORMAL_ITEM
    }
    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MenuItemVH ->{
                val item = data[position]
                if (item.enable) {
                    holder.iconView.setImageResource(item.enableIconResId)
                    holder.labelTextView.setText(item.enableLabelResId)
                    holder.labelTextView.setTextColor(holder.itemView.context.loadColor(R.color.iconEnableColor))
                    holder.itemView.isClickable=true
                    holder.itemView.isFocusable=true
                    holder.itemView.setOnClickListener {
                        item.action?.invoke()
                    }
                }else{
                    holder.iconView.setImageResource(item.disableIconResId)
                    holder.labelTextView.setText(item.disableLabelResId)
                    holder.labelTextView.setTextColor(holder.itemView.context.loadColor(R.color.iconDisableColor))
                    holder.itemView.isClickable=false
                    holder.itemView.isFocusable=false
                    holder.itemView.setOnClickListener(null)
                }
            }
            is LargeMenuItemVH ->{
                val item = data[position]
                holder.iconView.setImageResource(item.enableIconResId)
                holder.itemView.setOnClickListener {
                    item.action?.invoke()
                }
            }
        }

    }

}