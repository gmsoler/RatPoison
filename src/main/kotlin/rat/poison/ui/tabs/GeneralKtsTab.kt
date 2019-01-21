package rat.poison.ui.tabs

import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.kotcrab.vis.ui.util.Validators
import com.kotcrab.vis.ui.widget.*
import com.kotcrab.vis.ui.widget.tabbedpane.Tab
import org.jire.arrowhead.keyPressed
import rat.poison.App
import rat.poison.settings.FIRE_KEY
import rat.poison.settings.LEAGUE_MODE
import rat.poison.settings.MENU_KEY
import rat.poison.settings.VISUALS_TOGGLE_KEY
import rat.poison.ui.changed
import rat.poison.utils.ObservableBoolean

class GeneralKts : Tab(false, false) { //Aim.kts tab
    private val table = VisTable(true)

    //Init labels/sliders/boxes that show values here
    val leagueModeToggle = VisTextButton("LEAGUE_MODE", "toggle") //League_Mode
    val fireKeyField = VisValidatableTextField(Validators.FLOATS) //Activate_From_Fire_Key
    val visualsToggleKeyField = VisValidatableTextField(Validators.FLOATS) //Visuals_Toggle_Key
    val menuKeyField = VisValidatableTextField(Validators.FLOATS) //Menu_Key_Field

    init {
        //Create League_Mode Toggle
        //val leagueModeToggle = VisTextButton("LEAGUE_MODE", "toggle")
        if (LEAGUE_MODE) leagueModeToggle.toggle()
        leagueModeToggle.changed { _, _ ->
            if (true) { //type Any? changes didnt work im autistic //fix later
                LEAGUE_MODE = leagueModeToggle.isChecked//!LEAGUE_MODE
            }
        }

        //Create Fire_Key Input
        val fireKey = VisTable()
        val fireKeyLabel = VisLabel("Fire Key: ")
        //val fireKeyField = VisValidatableTextField(Validators.FLOATS)
        fireKeyField.text = FIRE_KEY.toString()
        fireKey.changed { _, _ ->
            if (fireKeyField.text.toIntOrNull() != null) {
                FIRE_KEY = fireKeyField.text.toInt()
            }
        }
        fireKey.add(fireKeyLabel)
        fireKey.add(fireKeyField).spaceRight(6F).width(40F)
        fireKey.add(LinkLabel("?", "http://cherrytree.at/misc/vk.htm"))

        //Create Visuals_Toggle_Key Input
        val visualsToggleKey = VisTable()
        val visualsToggleKeyLabel = VisLabel("Visuals Toggle Key: ")
        //val visualsToggleKeyField = VisValidatableTextField(Validators.FLOATS)
        visualsToggleKeyField.text = VISUALS_TOGGLE_KEY.toString()
        visualsToggleKey.changed { _, _ ->
            if (fireKeyField.text.toIntOrNull() != null) {
                VISUALS_TOGGLE_KEY = visualsToggleKeyField.text.toInt()
            }
        }
        visualsToggleKey.add(visualsToggleKeyLabel)
        visualsToggleKey.add(visualsToggleKeyField).spaceRight(6F).width(40F)
        visualsToggleKey.add(LinkLabel("?", "http://cherrytree.at/misc/vk.htm"))

        //Create Menu_Key Input
        val menuKey = VisTable()
        val menuKeyLabel = VisLabel("Menu Key: ")
        //val menuKeyField = VisValidatableTextField(Validators.FLOATS)
        menuKeyField.text = MENU_KEY.toString()
        menuKey.changed { _, _ ->
            if (menuKeyField.text.toIntOrNull() != null) {
                MENU_KEY = menuKeyField.text.toInt()
                App.Menu_Key = ObservableBoolean({ keyPressed(MENU_KEY) })
            }
        }
        menuKey.add(menuKeyLabel)
        menuKey.add(menuKeyField).spaceRight(6F).width(40F)
        menuKey.add(LinkLabel("?", "http://cherrytree.at/misc/vk.htm"))


        //Add all items to label for tabbed pane content
        table.add(leagueModeToggle).row() //Add League_Mode Toggle
        //Skipped Classic Offensive
        table.add(fireKey).row() //Add Fire_Key Input
        //Skipped Action_Log
        table.add(visualsToggleKey).row() //Add Visuals_Toggle_Key Input
        table.add(menuKey).row() //Add Menu_Key Input

    }

    override fun getContentTable(): Table? {
        return table
    }

    override fun getTabTitle(): String? {
        return "General.kts"
    }
}